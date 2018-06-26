package de.stockpicker.backend.controller.stock;

import de.stockpicker.backend.entity.Symbol;
import de.stockpicker.backend.entity.Trade;
import de.stockpicker.backend.exception.trade.SymbolNotFoundException;
import de.stockpicker.backend.repository.SymbolRepository;
import de.stockpicker.backend.repository.TradeRepository;
import de.stockpicker.backend.request.TradeRequest;
import de.stockpicker.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Date;
import java.util.Optional;

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
    public void createTrade(@RequestBody TradeRequest tradeRequest, Principal principal) {
        Optional<Symbol> symbolOptional = symbolRepository.findDistinctByKeyEquals(tradeRequest.getSymbol());
        if (symbolOptional.isPresent()) {
            
            Trade trade = new Trade();
            trade.setSymbol(symbolOptional.get());
            trade.setUser(userService.getUserByUsername(principal.getName()));
            trade.setVolume(tradeRequest.getVolume());
            trade.setOpened(new Date());
            trade.setOpenValue((long) 1000.0);

            tradeRepository.save(trade);
        } else {
            throw new SymbolNotFoundException(tradeRequest.getSymbol());
        }
    }
}
