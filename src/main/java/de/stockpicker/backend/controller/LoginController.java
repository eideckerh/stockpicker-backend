package de.stockpicker.backend.controller;

import de.stockpicker.backend.entity.User;
import de.stockpicker.backend.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.Produces;
import java.security.Principal;

@Slf4j
@RestController
public class LoginController {

    @Autowired
    UserService userService;

    @GetMapping(path = "/login")
    @Produces("application/json")
    @ApiOperation(value = "Endpunkt zur Prüfung von Benutzer / Passwort Kombination")
    public ResponseEntity<User> test(Principal principal) {
        User user = userService.getUserByUsername(principal.getName());
        log.info("logging in {}", user.getUsername());
        return ResponseEntity.ok(user);
    }
}
