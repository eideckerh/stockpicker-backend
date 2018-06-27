package de.stockpicker.backend.controller.stock;

import de.stockpicker.backend.client.alphavantage.webservice.batch.Client;
import de.stockpicker.backend.client.alphavantage.webservice.batch.Response;
import de.stockpicker.backend.exception.trade.SymbolNotFoundException;
import de.stockpicker.backend.repository.SymbolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stock/batch")
public class BatchController {

    @Autowired
    Client client;

    @Autowired
    SymbolRepository symbolRepository;

    @GetMapping
    public Response batch(@RequestParam("symbol") String symbol) {
        symbolRepository.findDistinctByKeyEquals(symbol).orElseThrow(() -> new SymbolNotFoundException(symbol));
        return client.query(symbol);
    }
}
