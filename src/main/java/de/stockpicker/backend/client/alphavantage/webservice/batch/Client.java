package de.stockpicker.backend.client.alphavantage.webservice.batch;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class Client {
    public Response query() {
        RestTemplate restTemplate = new RestTemplate();
        Response batchResponse = restTemplate.getForObject("https://www.alphavantage.co/query?function=BATCH_STOCK_QUOTES&symbols=MSFT,FB,AAPL&apikey=demo", Response.class);
        System.out.println(batchResponse);

        return batchResponse;
    }
}
