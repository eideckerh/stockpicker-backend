package de.stockpicker.backend.client.alphavantage.webservice;

import de.stockpicker.backend.entity.ApiKey;
import de.stockpicker.backend.repository.ApiKeyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ApiKeyService {
    @Autowired
    ApiKeyRepository apiKeyRepository;

    public String getApiKey() {
        ApiKey apiKey = apiKeyRepository.findTopByOrderByLastUseAsc();
        apiKey.setLastUse(new Date());
        apiKeyRepository.save(apiKey);
        return apiKey.getKey();
    }
}
