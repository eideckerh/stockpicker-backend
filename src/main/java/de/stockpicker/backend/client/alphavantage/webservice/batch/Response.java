package de.stockpicker.backend.client.alphavantage.webservice.batch;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.stockpicker.backend.client.alphavantage.webservice.MetaData;

import java.util.List;

/**
 * Klasse zur Deseralisierung der AlphaVantage Batch API
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Response {
    private MetaData metaData;
    private List<Quote> quotes;

    @JsonCreator
    public Response(@JsonProperty("Meta Data") MetaData metaData) {
        this.metaData = metaData;
    }

    @JsonProperty("metaData")
    public MetaData getMetaData() {
        return metaData;
    }

    @JsonProperty("Meta Data")
    public void setMetaData(MetaData metaData) {
        this.metaData = metaData;
    }

    @JsonProperty("quotes")
    public List<Quote> getQuotes() {
        return quotes;
    }

    @JsonProperty("Stock Quotes")
    public void setQuotes(List<Quote> quotes) {
        this.quotes = quotes;
    }
}
