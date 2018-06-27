package de.stockpicker.backend.exception.trade;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TradeNotFoundException extends RuntimeException {
    public TradeNotFoundException(long trade) {
        super("Trade " + trade + " not found");
    }
}
