package de.stockpicker.backend.client.alphavantage.webservice.timeseries;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Service zur Abfrage historischer Börsenkurse
 */
@Service("timeseriesClient")
public class Client extends de.stockpicker.backend.client.alphavantage.webservice.Client {
    /**
     * Abfrage historischer Börsenkurse von Aktien
     * @param symbol
     * @param interval
     * @param function
     * @return
     */
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

    /**
     * Abfrage historischer Börsenkurse von digitalen Währungen
     * @param symbol
     * @param interval
     * @param function
     * @param market
     * @return
     */
    public Response queryCrypto(String symbol, String interval, String function, String market) {
        UriComponentsBuilder uriComponentsBuilder =
                getPreparedUriBuilder()
                        .queryParam("function", function)
                        .queryParam("symbol", symbol)
                        .queryParam("interval", interval)
                        .queryParam("apikey", apiKeyService.getApiKey())
                        .queryParam("market", market);

        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(uriComponentsBuilder.build().toUri().toString(), Response.class);
    }
}
