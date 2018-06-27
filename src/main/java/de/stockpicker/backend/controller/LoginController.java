package de.stockpicker.backend.controller;

import de.stockpicker.backend.service.UserService;
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
    public ResponseEntity<String> test(Principal principal) {
        String name = userService.getUserByUsername(principal.getName()).getUsername();
        log.info("logging in {}", name);
        return ResponseEntity.ok("logged in as " + name);
    }
}
