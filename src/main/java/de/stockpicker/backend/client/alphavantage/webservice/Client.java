package de.stockpicker.backend.client.alphavantage.webservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.util.UriComponentsBuilder;

public class Client {
    @Value("${alphavantage.scheme}")
    protected String webserviceScheme;

    @Value("${alphavantage.url}")
    protected String webserviceUrl;

    @Value("${alphavantage.query}")
    protected String webserviceLocation;

    @Autowired
    protected ApiKeyService apiKeyService;

    protected UriComponentsBuilder getUriBuilder() {
        return UriComponentsBuilder.newInstance();
    }

    protected UriComponentsBuilder getPreparedUriBuilder() {
        return getUriBuilder().scheme(webserviceScheme).host(webserviceUrl).path(webserviceLocation);
    }
}
