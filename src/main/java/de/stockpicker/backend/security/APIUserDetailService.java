package de.stockpicker.backend.security;


import de.stockpicker.backend.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class APIUserDetailService implements UserDetailsService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName)
            throws UsernameNotFoundException {

        de.stockpicker.backend.user.Entity.User apiUser = this.userRepository.findDistinctByUsername(userName);
        logger.debug("Found user: " + apiUser.getUsername());
        return new User(apiUser.getUsername(), apiUser.getPassword(), true, true, true,
                true, AuthorityUtils.createAuthorityList("USER"));
    }
}
