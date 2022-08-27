package com.techelevator.tenmo;

import com.techelevator.tenmo.model.TransferStatus;
import com.techelevator.tenmo.model.TransferType;
import com.techelevator.tenmo.services.TransferStatusService;
import com.techelevator.tenmo.services.TransferTypeService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TransferStatusServiceTest {
    private TransferStatusService transferStatusService;

    @Before
    public void setup() {
        transferStatusService = new TransferStatusService("http://localhost:8080/");
    }

    //Need to Have TenmoApplication Running in order to test here

    @Test
    public void transferTypeServiceShouldReturnProperId() {
        Long expected = 1L;
        TransferStatus transferStatus = transferStatusService.getByType("Pending");
        Assert.assertEquals(expected,transferStatus.getTransferStatusId());

        expected = 2L;
        transferStatus = transferStatusService.getByType("Approved");
        Assert.assertEquals(expected,transferStatus.getTransferStatusId());

        expected = 3L;
        transferStatus = transferStatusService.getByType("Rejected");
        Assert.assertEquals(expected,transferStatus.getTransferStatusId());
    }
}
