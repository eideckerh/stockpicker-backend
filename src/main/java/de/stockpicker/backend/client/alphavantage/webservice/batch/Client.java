package de.stockpicker.backend.client.alphavantage.webservice.batch;

import de.stockpicker.backend.util.DateUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service("batchClient")
public class Client extends de.stockpicker.backend.client.alphavantage.webservice.Client {

    public Response query(String symbol) {

        UriComponentsBuilder uriComponentsBuilder =
                getPreparedUriBuilder()
                        .queryParam("function", "BATCH_STOCK_QUOTES")
                        .queryParam("symbols", symbol)
                        .queryParam("apikey", apiKeyService.getApiKey());

        RestTemplate restTemplate = new RestTemplate();
        Response batchResponse = DateUtil.convertBatchResponse(restTemplate.getForObject(uriComponentsBuilder.build().toUri().toString(), Response.class));

        return batchResponse;
    }

    public double getCurrentPrice(String symbol) {
        Response response = query(symbol);
        return Double.valueOf(response.getQuotes().get(0).getPrice());
    }
}
