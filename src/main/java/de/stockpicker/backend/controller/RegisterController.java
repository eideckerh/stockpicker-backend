package de.stockpicker.backend.controller;

import de.stockpicker.backend.entity.User;
import de.stockpicker.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    public ResponseEntity register(@RequestBody User user, BCryptPasswordEncoder bCryptPasswordEncoder) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRole("USER");
        user.setActive(false);

        if (userRepository.existsByUsername(user.getUsername())) {
            return ResponseEntity.badRequest().body("Username already exists");
        }
        userRepository.save(user);
        return ResponseEntity.noContent().build();
    }
}
