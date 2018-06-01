package de.stockpicker.backend.repository;

import de.stockpicker.backend.user.Entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findDistinctByUsername(String username);
}

