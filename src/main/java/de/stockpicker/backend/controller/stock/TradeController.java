package de.stockpicker.backend.controller.stock;

import de.stockpicker.backend.entity.Trade;
import de.stockpicker.backend.request.TradeRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping(path = "/trade")
public class TradeController {
    @PostMapping
    public void createTrade(@RequestBody TradeRequest tradeRequest, Principal principal) {
        System.out.println(tradeRequest.getSymbol());
        System.out.println(principal.getName());
    }
}
