package de.stockpicker.backend.repository;

import de.stockpicker.backend.entity.Symbol;
import de.stockpicker.backend.entity.SymbolType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SymbolRepository extends CrudRepository<Symbol, Long> {
    public Optional<Symbol> findDistinctByKeyEquals(String name);
}

