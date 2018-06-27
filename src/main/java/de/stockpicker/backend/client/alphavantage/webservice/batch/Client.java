package de.stockpicker.backend.client.alphavantage.webservice.batch;

import de.stockpicker.backend.client.alphavantage.webservice.ApiKeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class Client {

    @Value("${alphavantage.scheme}")
    String webserviceScheme;

    @Value("${alphavantage.url}")
    String webserviceUrl;

    @Value("${alphavantage.query}")
    String webserviceLocation;

    @Autowired
    ApiKeyService apiKeyService;

    public Response query(String symbol) {
        UriComponentsBuilder uriComponentsBuilder = getUriBuilder();
        uriComponentsBuilder.scheme(webserviceScheme).host(webserviceUrl).path(webserviceLocation);
        uriComponentsBuilder.queryParam("function", "BATCH_STOCK_QUOTES").queryParam("symbols", symbol).queryParam("apikey", apiKeyService.getApiKey());


        RestTemplate restTemplate = new RestTemplate();
        Response batchResponse = restTemplate.getForObject(uriComponentsBuilder.build().toUri().toString(), Response.class);

        return batchResponse;
    }

    private UriComponentsBuilder getUriBuilder() {
        return UriComponentsBuilder.newInstance();
    }
}
