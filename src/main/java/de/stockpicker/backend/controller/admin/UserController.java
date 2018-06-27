package de.stockpicker.backend.controller.admin;

import de.stockpicker.backend.entity.User;
import de.stockpicker.backend.exception.user.UserNotFoundException;
import de.stockpicker.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping(path = "/admin/users")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping
    public Iterable<User> listUsers() {
        return userRepository.findAll();
    }

    @PutMapping("/{userId:[\\d]+}")
    public ResponseEntity<User> updateUser(@PathVariable Long userId, @RequestBody User userRequest) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId.toString()));
        user.setActive(userRequest.isActive());
        user.setEmail(userRequest.getEmail());
        user.setFirstname(userRequest.getFirstname());
        user.setLastname(userRequest.getLastname());
        user.setRole(userRequest.getRole());
        userRepository.save(user);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{userId:[\\d]+}")
    public ResponseEntity<User> getOneUser(@PathVariable Long userId) {
        User user = userRepository.findById(userId).get();
        if (Objects.isNull(user)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{userId:[\\d]+}")
    public ResponseEntity<User> deleteOneUser(@PathVariable Long userId) {
        userRepository.deleteById(userId);
        return ResponseEntity.noContent().build();
    }
}
