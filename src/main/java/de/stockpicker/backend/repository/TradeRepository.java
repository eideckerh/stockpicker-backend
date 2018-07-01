package de.stockpicker.backend.repository;

import de.stockpicker.backend.entity.Trade;
import de.stockpicker.backend.entity.User;
import de.stockpicker.backend.response.SymbolStatistic;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TradeRepository extends CrudRepository<Trade, Long> {
    @Query(value = "select new de.stockpicker.backend.response.SymbolStatistic(trade.symbol, count (trade) as cnt) from Trade trade group by trade.symbol order by cnt")
    public List<SymbolStatistic> calculateMostTradedSymbols();

    public List<Trade> findAllByUserOrderByOpened(User user);

    public Optional<Trade> findTradeByIdAndUser(Long id, User user);

    public List<Trade> findTradesByClosedIsNullAndUser(User user);
}

