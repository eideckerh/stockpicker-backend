package de.stockpicker.backend.controller.admin;

import de.stockpicker.backend.entity.User;
import de.stockpicker.backend.exception.user.UserNotFoundException;
import de.stockpicker.backend.repository.UserRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping(path = "/admin/users")
@Api(value = "admin", description = "Endpunkt zur Verwaltung von Benutzern")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping
    @ApiOperation(value = "Zeige eine Liste aller Benutzer an", response = Iterable.class)
    public Iterable<User> listUsers() {
        return userRepository.findAll();
    }

    @PutMapping("/{userId:[\\d]+}")
    @ApiOperation(value = "Aktualisierung eines einzelnen Benutzers")
    public ResponseEntity<User> updateUser(@PathVariable Long userId, @RequestBody User userRequest) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId.toString()));
        userRequest.setId(userId);
        userRepository.save(userRequest);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{userId:[\\d]+}")
    @ApiOperation(value = "Zeige eine einzelnen Benutzer an", response = User.class)
    public ResponseEntity<User> getOneUser(@PathVariable Long userId) {
        User user = userRepository.findById(userId).get();
        if (Objects.isNull(user)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{userId:[\\d]+}")
    @ApiOperation(value = "Löscht einen einzelnen Benutzer")
    public ResponseEntity<User> deleteOneUser(@PathVariable Long userId) {
        userRepository.deleteById(userId);
        return ResponseEntity.noContent().build();
    }
}
