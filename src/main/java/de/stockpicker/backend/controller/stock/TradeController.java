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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.ws.rs.Produces;
import java.io.*;
import java.security.Principal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/trade")
@Api(value = "trade", description = "Endpunkt zur Eröffnung / Schließung und Abfrage von Trades")
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
    @Produces("application/json")
    @ApiOperation(value = "Eröffnet einen neuen Trade")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Erfolgreiche Eröffnung des Trades"),
            @ApiResponse(code = 401, message = "Authentifizierung nicht erfolgreich"),
            @ApiResponse(code = 403, message = "Authentifizierung nicht erfolgreich"),
            @ApiResponse(code = 404, message = "Der Index des zu eröffneten Trades existiert nicht")
    })
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

        //lade aktuellen Preis des Indexes
        trade.setOpenValue(batchClient.getCurrentPrice(symbol.getKey(), symbol.getType().getKey()));

        tradeRepository.save(trade);

        return ResponseEntity.created(
                ServletUriComponentsBuilder
                        .fromCurrentRequest().path("/{id}")
                        .buildAndExpand(trade.getId()).toUri())
                .build();
    }

    @PostMapping(path = "{id}/close")
    @Produces(value = "application/json")
    @ApiOperation(value = "Schließt einen Trade")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Erfolgreiche Rückgabe der offenen Trades"),
            @ApiResponse(code = 401, message = "Authentifizierung nicht erfolgreich"),
            @ApiResponse(code = 403, message = "Authentifizierung nicht erfolgreich"),
            @ApiResponse(code = 404, message = "Der zu schließende Trade wurde nicht gefunden")
    })
    public ResponseEntity<Trade> closeTrade(Principal principal, @PathVariable("id") Long id) {
        Trade trade = tradeRepository.findById(id).orElseThrow(() -> new TradeNotFoundException(id));
        trade.setClosed(new Date());
        trade.setCloseValue(batchClient.getCurrentPrice(trade.getSymbol().getKey(), trade.getSymbol().getType().getKey()));
        tradeRepository.save(trade);
        return ResponseEntity.ok(trade);
    }

    @GetMapping
    @Produces(value = "application/json")
    @ApiOperation(value = "Gibt alle Trades des Benutzers zurück")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Erfolgreiche Rückgabe der Trades"),
            @ApiResponse(code = 401, message = "Authentifizierung nicht erfolgreich"),
            @ApiResponse(code = 403, message = "Authentifizierung nicht erfolgreich")
    })
    public List<Trade> listAllTrades(Principal principal) {
        return tradeRepository.findAllByUserOrderByOpened(userService.getUserByPrincipal(principal));
    }

    @GetMapping(path = "/{id:[\\d]+}")
    @Produces(value = "application/json")
    @ApiOperation(value = "Gibt den angefragten Trade des Benutzers zurück")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Erfolgreiche Rückgabe des Trades"),
            @ApiResponse(code = 401, message = "Authentifizierung nicht erfolgreich"),
            @ApiResponse(code = 403, message = "Authentifizierung nicht erfolgreich"),
            @ApiResponse(code = 404, message = "Der Trade wurde nicht gefunden")
    })
    public Trade findTrade(@PathVariable("id") Long id, Principal principal) {
        return tradeRepository.findTradeByIdAndUser(id, userService.getUserByPrincipal(principal)).orElseThrow(() -> new TradeNotFoundException(id));
    }

    @GetMapping(path = "/open")
    @Produces(value = "application/json")
    @ApiOperation(value = "Gibt alle offenen Trades des Benutzers zurück")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Erfolgreiche Rückgabe der offenen Trades"),
            @ApiResponse(code = 401, message = "Authentifizierung nicht erfolgreich"),
            @ApiResponse(code = 403, message = "Authentifizierung nicht erfolgreich")
    })
    public List<Trade> findOpenTrades(Principal principal) {
        return tradeRepository.findTradesByClosedIsNullAndUser(userService.getUserByPrincipal(principal));
    }

    @GetMapping(path = "/report", headers = "Accept=*/*")
    @Produces(value = "application/pdf")
    @ApiOperation(value = "Gibt eine PDF-Übersicht der Trades eines Benutzers aus")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Erfolgreiche Generierung der Übersicht"),
            @ApiResponse(code = 401, message = "Authentifizierung nicht erfolgreich"),
            @ApiResponse(code = 403, message = "Authentifizierung nicht erfolgreich"),
            @ApiResponse(code = 500, message = "Bei der Generierung des Reports ist ein Fehler aufgetreten")
    })
    public ResponseEntity<InputStreamResource> tradeReport(Principal principal) {
        //Parameter setzten (Trades)
        Map<String, Object> pdfParams = new HashMap<>();
        pdfParams.put("trades", tradeRepository.findAllByUserOrderByOpened(userService.getUserByPrincipal(principal)));

        try {
            //PDF generieren und ausgeben
            File file = pdfGenerator.createPdf("pdf", pdfParams);
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(new FileInputStream(file)));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
