package de.stockpicker.backend.controller.stock;

import de.stockpicker.backend.client.alphavantage.webservice.batch.Client;
import de.stockpicker.backend.entity.Symbol;
import de.stockpicker.backend.entity.Trade;
import de.stockpicker.backend.exception.trade.InvalidVolumeException;
import de.stockpicker.backend.exception.trade.SymbolNotFoundException;
import de.stockpicker.backend.exception.trade.TradeNotFoundException;
import de.stockpicker.backend.repository.SymbolRepository;
import de.stockpicker.backend.repository.TradeRepository;
import de.stockpicker.backend.request.TradeRequest;
import de.stockpicker.backend.service.UserService;
import de.stockpicker.backend.util.PDFGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.print.attribute.standard.Media;
import javax.ws.rs.Produces;
import java.io.*;
import java.security.Principal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/trade")
public class TradeController {
    @Autowired
    SymbolRepository symbolRepository;

    @Autowired
    TradeRepository tradeRepository;

    @Autowired
    UserService userService;

    @Autowired
    Client batchClient;

    @Autowired
    PDFGenerator pdfGenerator;

    @PostMapping
    public ResponseEntity createTrade(@RequestBody TradeRequest tradeRequest, Principal principal) {
        if (tradeRequest.getVolume() <= 0) {
            throw new InvalidVolumeException(tradeRequest.getVolume());
        }
        Symbol symbol = symbolRepository.findDistinctByKeyEquals(tradeRequest.getSymbol()).orElseThrow(() -> new SymbolNotFoundException(tradeRequest.getSymbol()));

        Trade trade = new Trade();
        trade.setSymbol(symbol);
        trade.setUser(userService.getUserByPrincipal(principal));
        trade.setVolume(tradeRequest.getVolume());
        trade.setOpened(new Date());
        trade.setOpenValue(batchClient.getCurrentPrice(symbol.getKey(), symbol.getType().getKey()));

        tradeRepository.save(trade);

        return ResponseEntity.created(
                ServletUriComponentsBuilder
                        .fromCurrentRequest().path("/{id}")
                        .buildAndExpand(trade.getId()).toUri())
                .build();
    }

    @PostMapping(path = "{id}/close")
    public ResponseEntity<Trade> closeTrade(Principal principal, @PathVariable("id") Long id) {
        Trade trade = tradeRepository.findById(id).orElseThrow(() -> new TradeNotFoundException(id));
        trade.setClosed(new Date());
        trade.setCloseValue(batchClient.getCurrentPrice(trade.getSymbol().getKey(), trade.getSymbol().getType().getKey()));
        tradeRepository.save(trade);
        return ResponseEntity.ok(trade);
    }

    @GetMapping
    public List<Trade> listAllTrades(Principal principal) {
        return tradeRepository.findAllByUserOrderByOpened(userService.getUserByPrincipal(principal));
    }

    @GetMapping(path = "/{id:[\\d]+}")
    public Trade findTrade(@PathVariable("id") Long id, Principal principal) {
        return tradeRepository.findTradeByIdAndUser(id, userService.getUserByPrincipal(principal)).orElseThrow(() -> new TradeNotFoundException(id));
    }

    @GetMapping(path = "/open")
    public List<Trade> findOpenTrades(Principal principal) {
        return tradeRepository.findTradesByClosedIsNullAndUser(userService.getUserByPrincipal(principal));
    }

    @GetMapping(path = "/report", headers = "Accept=*/*")
    public ResponseEntity<InputStreamResource> tradeReport(Principal principal) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline, filename=trades.pdf");

        Map<String, Object> pdfParams = new HashMap<>();

        pdfParams.put("trades", tradeRepository.findAllByUserOrderByOpened(userService.getUserByPrincipal(principal)));

        try {
            File file = pdfGenerator.createPdf("pdf", pdfParams);
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(new FileInputStream(file)));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
