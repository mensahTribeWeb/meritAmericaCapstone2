package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.TransferStatus;
import com.techelevator.tenmo.model.TransferType;
import com.techelevator.util.BasicLogger;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

public class TransferStatusService {
    private final String baseUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    public TransferStatusService(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public TransferStatus getByType(String status) {
        TransferStatus transferStatus = null;
        try {
            transferStatus = restTemplate.getForObject(baseUrl + "transfers/status/?status_like=" + status, TransferStatus.class);
        } catch (RestClientResponseException | ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }
        return transferStatus;
    }

    public TransferStatus getById(int id) {
        TransferStatus transferStatus = null;
        try {
            transferStatus = restTemplate.getForObject(baseUrl + "transfers/status/" + id, TransferStatus.class);
        } catch (RestClientResponseException | ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }
        return transferStatus;

    }


}
