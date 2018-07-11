package de.stockpicker.backend.controller;

import de.stockpicker.backend.entity.User;
import de.stockpicker.backend.repository.UserRepository;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;

@RestController
@Api(value = "register", description = "Endpunkt zur Registrierung von neuen Benutzern")
public class RegisterController {

    @Autowired
    UserRepository userRepository;

    @PostMapping(path = "/register")
    @Produces(value = "application/json")
    public ResponseEntity register(@RequestBody User user, BCryptPasswordEncoder bCryptPasswordEncoder) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRole("USER");
        user.setActive(false);

        if (userRepository.existsByUsernameOrEmail(user.getUsername(), user.getEmail())) {
            return ResponseEntity.badRequest().body("Username or Email already exists");
        }
        userRepository.save(user);
        return ResponseEntity.noContent().build();
    }
}
