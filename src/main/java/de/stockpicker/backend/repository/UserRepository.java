package de.stockpicker.backend.repository;

import de.stockpicker.backend.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findDistinctByUsernameAndActiveIsTrue(String username);
}

