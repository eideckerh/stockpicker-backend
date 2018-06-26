package de.stockpicker.backend.service;

import de.stockpicker.backend.entity.User;
import de.stockpicker.backend.exception.user.UserNotFoundException;
import de.stockpicker.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User getUserByUsername(String username) {
        Optional<User> userOptional = userRepository.findDistinctByUsernameAndActiveIsTrue(username);
        if (userOptional.isPresent()) {
            return userOptional.get();
        } else {
            throw new UserNotFoundException(username);
        }
    }
}
