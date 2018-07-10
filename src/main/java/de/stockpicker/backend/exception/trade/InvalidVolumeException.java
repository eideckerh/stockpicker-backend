package de.stockpicker.backend.exception.trade;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class InvalidVolumeException extends RuntimeException {
    public InvalidVolumeException(double volume) {
        super("Volume " + volume + " not accepted. Volume has to be greater then 0");
    }
}
