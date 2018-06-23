package de.stockpicker.backend.repository;

import de.stockpicker.backend.entity.SymbolType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SymbolTypeRepository extends CrudRepository<SymbolType, Long> {
    public Optional<SymbolType> findDistinctByKeyEquals(String name);
}

