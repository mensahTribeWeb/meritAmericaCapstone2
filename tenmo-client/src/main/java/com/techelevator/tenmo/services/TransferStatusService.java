package com.techelevator.tenmo.services;

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

    public Transfer getTransferByStatus(String status) {
        Transfer transfer= null;
        try
        {
            transfer = restTemplate .getForObject(baseUrl + "transfers/?status_like=" + status, Transfer.class);
        }
        catch (RestClientResponseException | ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }
        return transfer;
    }
}
