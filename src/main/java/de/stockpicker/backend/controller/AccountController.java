package de.stockpicker.backend.controller;

import de.stockpicker.backend.entity.User;
import de.stockpicker.backend.repository.TradeRepository;
import de.stockpicker.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping(path = "/account")
public class AccountController {
    @Autowired
    TradeRepository tradeRepository;

    @Autowired
    UserService userService;

    @GetMapping(path = "balance")
    public double currentBalance(Principal principal) {
        User user = userService.getUserByPrincipal(principal);
        return tradeRepository.calculateTradeProfit(user) + user.getEntryFee();
    }
}
