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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import javax.ws.rs.PathParam;
import java.security.Principal;
import java.util.Date;
import java.util.List;

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

    @PostMapping
    public ResponseEntity createTrade(@RequestBody TradeRequest tradeRequest, Principal principal) {
        if(tradeRequest.getVolume() <= 0) {
            throw new InvalidVolumeException(tradeRequest.getVolume());
        }
        Symbol symbol = symbolRepository.findDistinctByKeyEquals(tradeRequest.getSymbol()).orElseThrow(() -> new SymbolNotFoundException(tradeRequest.getSymbol()));

        Trade trade = new Trade();
        trade.setSymbol(symbol);
        trade.setUser(userService.getUserByPrincipal(principal));
        trade.setVolume(tradeRequest.getVolume());
        trade.setOpened(new Date());
        trade.setOpenValue(batchClient.getCurrentPrice(symbol.getKey()));

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
        trade.setCloseValue(batchClient.getCurrentPrice(trade.getSymbol().getKey()));
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
}
