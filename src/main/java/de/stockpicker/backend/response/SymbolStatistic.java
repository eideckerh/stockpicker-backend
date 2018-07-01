package de.stockpicker.backend.response;

import de.stockpicker.backend.entity.Symbol;
import lombok.Data;

@Data
public class SymbolStatistic {
    private Symbol symbol;
    private long count;

    public SymbolStatistic(Symbol symbol, long count) {
        this.symbol = symbol;
        this.count = count;
    }
}
