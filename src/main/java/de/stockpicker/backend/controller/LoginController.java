package de.stockpicker.backend.controller;

import de.stockpicker.backend.repository.UserRepository;
import de.stockpicker.backend.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.Produces;
import java.security.Principal;

@RestController
public class LoginController {

    @Autowired
    UserRepository userRepository;

    @GetMapping(path = "/login")
    @Produces("application/json")
    public User test(Principal principal) {
        return userRepository.findDistinctByUsernameAndActiveIsTrue(principal.getName());
    }
}
