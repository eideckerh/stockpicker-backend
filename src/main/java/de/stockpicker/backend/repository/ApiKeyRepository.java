package de.stockpicker.backend.repository;

import de.stockpicker.backend.entity.ApiKey;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApiKeyRepository extends CrudRepository<ApiKey, Long> {

}

