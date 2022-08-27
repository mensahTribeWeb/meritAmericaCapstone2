package com.techelevator.tenmo;

import com.techelevator.tenmo.model.TransferType;
import com.techelevator.tenmo.services.TransferTypeService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TransferTypeServiceTest {

    private TransferTypeService transferTypeService;

    @Before
    public void setup() {
        transferTypeService = new TransferTypeService("http://localhost:8080/");
    }

    //Need to Have TenmoApplication Running in order to test here

    @Test
    public void transferTypeServiceShouldReturnProperId() {
        Long expected = 1L;
        TransferType transferType = transferTypeService.getByType("Request");
        Assert.assertEquals(expected,transferType.getTransferTypeId());

        expected = 2L;
        transferType = transferTypeService.getByType("Send");
        Assert.assertEquals(expected,transferType.getTransferTypeId());
    }

}
