package de.stockpicker.backend.controller.stock;

import de.stockpicker.backend.client.alphavantage.webservice.timeseries.Client;
import de.stockpicker.backend.client.alphavantage.webservice.timeseries.Response;
import de.stockpicker.backend.entity.Symbol;
import de.stockpicker.backend.exception.trade.SymbolNotFoundException;
import de.stockpicker.backend.repository.SymbolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stock/timeserie")
public class TimeSerieController {

    @Autowired
    @Qualifier("timeseriesClient")
    Client client;
    @Autowired
    SymbolRepository symbolRepository;

    @GetMapping
    public Response timeSerie(@RequestParam("symbol") String symbol, @RequestParam("function") String function, @RequestParam("interval") String interval) {
        Symbol symbolEntity = symbolRepository.findDistinctByKeyEquals(symbol).orElseThrow(() -> new SymbolNotFoundException(symbol));
        return client.query(symbol, interval, function);
    }
}
