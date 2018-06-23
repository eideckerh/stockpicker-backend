package de.stockpicker.backend.controller.admin;

import de.stockpicker.backend.entity.User;
import de.stockpicker.backend.exception.user.UserNotFoundException;
import de.stockpicker.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/admin/users")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping
    public Iterable<User> listUsers() {
        return userRepository.findAll();
    }

    @PostMapping("/{userId:[\\d]+}")
    public void updateUser(@PathVariable Long userId, @RequestBody User userRequest) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId.toString()));
        user.setActive(userRequest.isActive());
        user.setEmail(userRequest.getEmail());
        user.setFirstname(userRequest.getFirstname());
        user.setLastname(userRequest.getLastname());
        user.setRole(userRequest.getRole());
        userRepository.save(user);

    }

    @GetMapping("/{userId:[\\d]+}")
    public User getOneUser(@PathVariable Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId.toString()));
    }

    @DeleteMapping("/{userId:[\\d]+}")
    public void deleteOneUser(@PathVariable Long userId) {
        userRepository.deleteById(userId);
    }
}
