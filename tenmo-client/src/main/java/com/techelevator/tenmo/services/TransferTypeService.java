package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.TransferType;
import com.techelevator.util.BasicLogger;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

public class TransferTypeService {
    private final String baseUrl;
    //private String authToken;
    private final RestTemplate restTemplate = new RestTemplate();

    public TransferTypeService(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public TransferType getByType(String type) {
        TransferType transferType = null;
        try {
            transferType = restTemplate.getForObject(baseUrl + "transfers/type/?type_like=" + type, TransferType.class);
        } catch (RestClientResponseException | ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }
        return transferType;
    }

    public TransferType getById(int id) {
        TransferType transferType = null;
        try {
            transferType = restTemplate.getForObject(baseUrl + "transfers/type/" + id, TransferType.class);
        } catch (RestClientResponseException | ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }
        return transferType;

    }
}
