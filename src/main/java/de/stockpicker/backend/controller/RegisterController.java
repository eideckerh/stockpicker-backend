package de.stockpicker.backend.controller;

import de.stockpicker.backend.repository.UserRepository;
import de.stockpicker.backend.user.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.Consumes;

@RestController
public class RegisterController {

    @Autowired
    UserRepository userRepository;

    @PostMapping(path = "/register")
    @Consumes(value = "application/json")
    public boolean register(@RequestBody User user) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        userRepository.save(user);
        return true;
    }
}
