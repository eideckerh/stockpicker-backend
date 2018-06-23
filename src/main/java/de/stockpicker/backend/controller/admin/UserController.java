package de.stockpicker.backend.controller.admin;

import de.stockpicker.backend.entity.User;
import de.stockpicker.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/admin/users")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping
    public Iterable<User> listUsers() {
        return userRepository.findAll();
    }

    @PostMapping("/{userId}")
    public void updateUser(@PathVariable Long userId, @RequestBody User userRequest) {
        Optional<User> userOptional = userRepository.findById(userId);
        System.out.println("Optional" + userOptional.isPresent());
        if(userOptional.isPresent()) {
            User user = userOptional.get();
            user.setActive(userRequest.isActive());
            user.setEmail(userRequest.getEmail());
            user.setFirstname(userRequest.getFirstname());
            user.setLastname(userRequest.getLastname());
            user.setRole(userRequest.getRole());
            userRepository.save(user);
        }
    }

    @GetMapping("/{userId}")
    public Optional<User> getOneUser(@PathVariable Long userId) {
        return userRepository.findById(userId);
    }

    @DeleteMapping("/{userId}")
    public void deleteOneUser(@PathVariable Long userId) {
        userRepository.deleteById(userId);
    }
}
