package de.stockpicker.backend.client.alphavantage.webservice.timeseries;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.util.Date;

/**
 * Klasse zur Deseralisierung der AlphaVantage Batch API
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Quote {
    private String open;
    private String high;
    private String close;
    private String low;
    private String volume;
    private String date;

    @JsonProperty("open")
    public String getOpen() {
        return open;
    }

    @JsonProperty("1. open")
    public void setOpen(String open) {
        this.open = open;
    }

    @JsonProperty("high")
    public String getHigh() {
        return high;
    }

    @JsonProperty("2. high")
    public void setHigh(String high) {
        this.high = high;
    }

    @JsonProperty("close")
    public String getClose() {
        return close;
    }

    @JsonProperty("4. close")
    public void setClose(String close) {
        this.close = close;
    }

    @JsonProperty("low")
    public String getLow() {
        return low;
    }

    @JsonProperty("3. low")
    public void setLow(String low) {
        this.low = low;
    }

    @JsonProperty("volume")
    public String getVolume() {
        return volume;
    }

    @JsonProperty("5. volume")
    public void setVolume(String volume) {
        this.volume = volume;
    }

    @JsonSetter("1b. price (USD)")
    public void setCryptoPrices(String price) {
        this.open = price;
        this.high = price;
        this.close = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
