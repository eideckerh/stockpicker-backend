package de.stockpicker.backend.client.alphavantage.webservice.timeseries;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service("timeseriesClient")
public class Client extends de.stockpicker.backend.client.alphavantage.webservice.Client {
    public Response query(String symbol, String interval, String function) {
        UriComponentsBuilder uriComponentsBuilder =
                getPreparedUriBuilder()
                        .queryParam("function", function)
                        .queryParam("symbol", symbol)
                        .queryParam("interval", interval)
                        .queryParam("apikey", apiKeyService.getApiKey());

        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(uriComponentsBuilder.build().toUri().toString(), Response.class);
    }
}
