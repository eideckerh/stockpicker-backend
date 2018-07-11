package de.stockpicker.backend.client.alphavantage.webservice.batch;

import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * Klasse zur Deseralisierung der AlphaVantage Batch API
 */
public class Quote {
    private String symbol;
    private String price;
    private String volume;
    private String timestamp;

    @JsonProperty("symbol")
    public String getSymbol() {
        return symbol;
    }

    @JsonProperty("1. symbol")
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    @JsonProperty("price")
    public String getPrice() {
        return price;
    }

    @JsonProperty("2. price")
    public void setPrice(String price) {
        this.price = price;
    }

    @JsonProperty("volume")
    public String getVolume() {
        return volume;
    }

    @JsonProperty("3. volume")
    public void setVolume(String volume) {
        this.volume = volume;
    }

    @JsonProperty("timestamp")
    public String getTimestamp() {
        return timestamp;
    }

    @JsonProperty("4. timestamp")
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
