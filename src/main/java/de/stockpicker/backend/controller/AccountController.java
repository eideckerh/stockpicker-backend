package de.stockpicker.backend.controller;

import de.stockpicker.backend.entity.User;
import de.stockpicker.backend.repository.TradeRepository;
import de.stockpicker.backend.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping(path = "/account")
@Api(value = "account", description = "Abfrage des Accounts")
public class AccountController {
    @Autowired
    TradeRepository tradeRepository;

    @Autowired
    UserService userService;

    @GetMapping(path = "balance")
    @ApiOperation(value = "Gibt den aktuellen Kontostand des Benutzers zurück")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Erfolgreiche Ermittlung des Kontostands"),
            @ApiResponse(code = 401, message = "Authentifizierung nicht erfolgreich"),
            @ApiResponse(code = 403, message = "Authentifizierung nicht erfolgreich")
    })
    public double currentBalance(Principal principal) {
        User user = userService.getUserByPrincipal(principal);
        return tradeRepository.calculateTradeProfit(user) + user.getEntryFee();
    }
}
