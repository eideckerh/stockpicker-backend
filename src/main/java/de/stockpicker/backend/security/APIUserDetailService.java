package de.stockpicker.backend.security;


import de.stockpicker.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Service zur Ermittlung eines Benutzers für die Basic Authentifizierung
 */
@Service
public class APIUserDetailService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String userName)
            throws UsernameNotFoundException {

        de.stockpicker.backend.entity.User apiUser = userService.getUserByUsername(userName);
        return new User(apiUser.getUsername(), apiUser.getPassword(), true, true, true,
                true, AuthorityUtils.createAuthorityList(apiUser.getRole()));
    }
}
