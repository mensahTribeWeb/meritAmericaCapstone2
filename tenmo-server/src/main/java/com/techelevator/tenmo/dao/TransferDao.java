package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


import java.util.List;

public interface TransferDao  {


    List<Transfer>listAll ();


    void sendTransfer(Transfer transfer);


    List<Transfer> getTransferHistory(Long id);
    Transfer getTransferDetails(Long id);


    Transfer getTransferDetails(int id);
}
