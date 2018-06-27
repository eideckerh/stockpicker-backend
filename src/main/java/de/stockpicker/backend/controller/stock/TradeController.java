package de.stockpicker.backend.controller.stock;

import de.stockpicker.backend.client.alphavantage.webservice.batch.Response;
import de.stockpicker.backend.entity.Symbol;
import de.stockpicker.backend.entity.Trade;
import de.stockpicker.backend.exception.trade.SymbolNotFoundException;
import de.stockpicker.backend.repository.SymbolRepository;
import de.stockpicker.backend.repository.TradeRepository;
import de.stockpicker.backend.request.TradeRequest;
import de.stockpicker.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.security.Principal;
import java.util.Date;

@RestController
@RequestMapping(path = "/trade")
public class TradeController {
    @Autowired
    SymbolRepository symbolRepository;

    @Autowired
    TradeRepository tradeRepository;

    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity createTrade(UriComponentsBuilder uriComponentsBuilder, @RequestBody TradeRequest tradeRequest, Principal principal) {
        Symbol symbol = symbolRepository.findDistinctByKeyEquals(tradeRequest.getSymbol()).orElseThrow(() -> new SymbolNotFoundException(tradeRequest.getSymbol()));

        Trade trade = new Trade();
        trade.setSymbol(symbol);
        trade.setUser(userService.getUserByUsername(principal.getName()));
        trade.setVolume(tradeRequest.getVolume());
        trade.setOpened(new Date());
        trade.setOpenValue((long) 1000.0);

        tradeRepository.save(trade);

        return ResponseEntity.created(
                ServletUriComponentsBuilder
                        .fromCurrentRequest().path("/{id}")
                        .buildAndExpand(trade.getId()).toUri())
                .build();
    }
}
