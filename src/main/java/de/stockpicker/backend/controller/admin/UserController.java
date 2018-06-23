package de.stockpicker.backend.controller.admin;

import de.stockpicker.backend.entity.User;
import de.stockpicker.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping(path = "/admin/users")
    public Iterable<User> listUsers() {
        return userRepository.findAll();
    }
}
