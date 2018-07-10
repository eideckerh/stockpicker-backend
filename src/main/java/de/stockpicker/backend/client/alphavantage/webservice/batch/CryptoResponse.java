package de.stockpicker.backend.client.alphavantage.webservice.batch;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CryptoResponse {
    private double price;

    @JsonProperty("price")
    public double getPrice() {
        return price;
    }

    @JsonProperty("5. Exchange Rate")
    public void setPrice(double price) {
        this.price = price;
    }
}
