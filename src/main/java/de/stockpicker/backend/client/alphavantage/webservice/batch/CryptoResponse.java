package de.stockpicker.backend.client.alphavantage.webservice.batch;


import com.fasterxml.jackson.annotation.*;

/**
 * Klasse zur Deseralisierung der AlphaVantage Batch API
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeName("Realtime Currency Exchange Rate")
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
public class CryptoResponse {
    private double price;

    public CryptoResponse(@JsonProperty("5. Exchange Rate") double price) {
        this.price = price;
    }

    @JsonProperty("price")
    public double getPrice() {
        return price;
    }

    @JsonProperty("5. Exchange Rate")
    public void setPrice(double price) {
        this.price = price;
    }
}
