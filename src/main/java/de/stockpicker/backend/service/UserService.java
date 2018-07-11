package de.stockpicker.backend.service;

import de.stockpicker.backend.entity.User;
import de.stockpicker.backend.exception.user.UserNotFoundException;
import de.stockpicker.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Optional;

/**
 * Service zur Suche eines Benutzers
 */
@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    /**
     * Sucht einen Benutzer anhand des Benutzernamens
     * @param username Benutzername
     * @return
     */
    public User getUserByUsername(String username) {
        Optional<User> userOptional = userRepository.findDistinctByUsernameAndActiveIsTrue(username);
        if (userOptional.isPresent()) {
            return userOptional.get();
        } else {
            throw new UserNotFoundException(username);
        }
    }

    /**
     * Suche einen Benutzer auf Basis des übergebenen Pricipals
     * @param principal Prinzipal des zu suchenden Benutzers
     * @return
     */
    public User getUserByPrincipal(Principal principal) {
        return getUserByUsername(principal.getName());
    }
}
