package de.stockpicker.backend.repository;

import de.stockpicker.backend.entity.Trade;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TradeRepository extends CrudRepository<Trade, Long> {

}

