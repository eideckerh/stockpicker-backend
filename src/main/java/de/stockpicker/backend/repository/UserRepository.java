package de.stockpicker.backend.repository;

import de.stockpicker.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findDistinctByUsernameAndActiveIsTrue(String username);
}

