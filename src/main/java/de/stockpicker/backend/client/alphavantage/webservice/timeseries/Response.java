package de.stockpicker.backend.client.alphavantage.webservice.timeseries;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.stockpicker.backend.client.alphavantage.webservice.MetaData;
import de.stockpicker.backend.util.DateUtil;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Klasse zur Deseralisierung der AlphaVantage Batch API
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Response {
    private MetaData metaData;
    private Map<String, Quote> quotes = new HashMap<>();

    @JsonProperty("metaData")
    public MetaData getMetaData() {
        return metaData;
    }

    @JsonProperty("Meta Data")
    public void setMetaData(MetaData metaData) {
        this.metaData = metaData;
    }

    @JsonProperty("quotes")
    public Map<String, Quote> getQuotes() {
        return quotes;
    }

    @JsonAnySetter
    public void handleUnknownProperty(String key, Object value) {
        ObjectMapper mapper = new ObjectMapper();

        if (key.contains("Time Series") && value instanceof Map) {
            Map<String, Object> objectMap = (Map<String, Object>) value;
            for (Map.Entry<String, Object> entry : objectMap.entrySet()) {

                Quote quote = mapper.convertValue(entry.getValue(), Quote.class);
                quote.setDate(DateUtil.convertDate(entry.getKey(), "US/Eastern"));
                quotes.put(DateUtil.convertDate(entry.getKey(), "US/Eastern"), quote);
            }
        }
    }
}
