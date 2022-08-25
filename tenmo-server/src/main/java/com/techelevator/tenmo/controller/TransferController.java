package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.TransferDao;
import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@PreAuthorize("isAuthenticated()")
public class TransferController {
    TransferDao transferDao;


//
//    @GetMapping ("/balance/{id}")
//    public BigDecimal getBalance(@PathVariable Long id){
//        BigDecimal balance = AccountDao.getTransferHistory(Long id);
//        return balance;
//    }
//
//    //V. The sender's account balance is decreased by the amount of the transfer.
//    @PutMapping("/tenmo_user/balance")
//    public void transfer.Create(@RequestBody Transfer transfer){
//        dao.update
//    }

}
