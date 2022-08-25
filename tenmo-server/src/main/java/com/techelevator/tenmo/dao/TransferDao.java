package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;


import java.util.List;

public interface TransferDao {

    void sendTransfer(Transfer transfer);

    List<Transfer> getTransferHistory(Long id);
    Transfer getTransferDetails(Long id);
}
