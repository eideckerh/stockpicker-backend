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

    public CryptoResponse queryCrypto(String symbol) {
        UriComponentsBuilder uriComponentsBuilder =
                getPreparedUriBuilder()
                        .queryParam("function", "CURRENCY_EXCHANGE_RATE")
                        .queryParam("from_currency", symbol)
                        .queryParam("to_currency" ,"USD")
                        .queryParam("apikey", apiKeyService.getApiKey());

        RestTemplate restTemplate = new RestTemplate();
        CryptoResponse cryptoResponse = restTemplate.getForObject(uriComponentsBuilder.build().toUri().toString(), CryptoResponse.class);
        return cryptoResponse;
    }

    public double getCurrentPrice(String symbol, String symbolType) {
        if(symbolType.equals("STOCK")) {
            Response response = query(symbol);
            return Double.valueOf(response.getQuotes().get(0).getPrice());
        }
        else {
            return queryCrypto(symbol).getPrice();
        }
    }
}
