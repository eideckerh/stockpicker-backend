package de.stockpicker.backend.controller.stock;

import de.stockpicker.backend.client.alphavantage.webservice.batch.Client;
import de.stockpicker.backend.client.alphavantage.webservice.batch.Response;
import de.stockpicker.backend.entity.Symbol;
import de.stockpicker.backend.exception.trade.SymbolNotFoundException;
import de.stockpicker.backend.repository.SymbolRepository;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.Produces;

@RestController
@RequestMapping("/stock/batch")
@Api(value = "stock", description = "Abfrage aktueller Börsenkurse")
public class BatchController {

    @Autowired
    @Qualifier("batchClient")
    Client client;

    @Autowired
    SymbolRepository symbolRepository;

    @GetMapping
    @Produces(value = "application/json")
    @ApiOperation(value = "Liefert aktuelle Börsenkurte", notes = "Liefert die aktuellen Börsenkurse der übergebenen Indizies")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Erfolgreiche Abfrage der Kurse", response = Response.class),
            @ApiResponse(code = 401, message = "Authentifizierung nicht erfolgreich"),
            @ApiResponse(code = 403, message = "Authentifizierung nicht erfolgreich"),
            @ApiResponse(code = 404, message = "Der angefrage Index existiert nicht")
    })
    public double batch(@ApiParam(value = "Abzufragender Index", required = true) @RequestParam("symbol") String symbol) {
        Symbol symbolEntity = symbolRepository.findDistinctByKeyEquals(symbol).orElseThrow(() -> new SymbolNotFoundException(symbol));
        return client.getCurrentPrice(symbolEntity.getKey(), symbolEntity.getType().getKey());
    }
}
