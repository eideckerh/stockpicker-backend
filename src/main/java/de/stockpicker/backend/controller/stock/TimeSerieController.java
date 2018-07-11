package de.stockpicker.backend.controller.stock;

import de.stockpicker.backend.client.alphavantage.webservice.timeseries.Client;
import de.stockpicker.backend.client.alphavantage.webservice.timeseries.Response;
import de.stockpicker.backend.entity.Symbol;
import de.stockpicker.backend.exception.trade.SymbolNotFoundException;
import de.stockpicker.backend.repository.SymbolRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.Produces;

@RestController
@RequestMapping("/stock/timeserie")
@Api(value = "timeseries", description = "Ermittlung der historischen Börsenkurse")
public class TimeSerieController {

    @Autowired
    @Qualifier("timeseriesClient")
    Client client;
    @Autowired
    SymbolRepository symbolRepository;

    @GetMapping
    @Produces(value = "application/json")
    @ApiOperation(value = "Liefert historische Börsenkurse", notes = "Liefert die historischen Börsenkurse des übergebenen Index")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Erfolgreiche Abfrage der Kurse", response = Response.class),
            @ApiResponse(code = 401, message = "Authentifizierung nicht erfolgreich"),
            @ApiResponse(code = 403, message = "Authentifizierung nicht erfolgreich"),
            @ApiResponse(code = 404, message = "Der angefrage Index existiert nicht")
    })
    public Response timeSerie(@RequestParam("symbol") String symbol, @RequestParam("function") String function, @RequestParam("interval") String interval) {
        Symbol symbolEntity = symbolRepository.findDistinctByKeyEquals(symbol).orElseThrow(() -> new SymbolNotFoundException(symbol));

        //abfrage der börsenkurse aufgrund des Index-Typs
        if(symbolEntity.getType().getKey().equals("DIGITAL_CURRENCY")) {
            function = "DIGITAL_CURRENCY_INTRADAY";
           return client.queryCrypto(symbol, interval, function, "EUR");
        }
        else {
            return client.query(symbol, interval, function);
        }
    }
}
