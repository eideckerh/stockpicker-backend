package de.stockpicker.backend.request;

/**
 * Klasse zur Datenübertragung bei Eröffnung eines Trades
 */
public class TradeRequest {
    private String symbol;
    private double volume;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }
}
