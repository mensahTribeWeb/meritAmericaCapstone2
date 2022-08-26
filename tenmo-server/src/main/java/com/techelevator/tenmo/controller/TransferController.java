package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.JdbcTransferDao;
import com.techelevator.tenmo.dao.TransferDao;
import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@PreAuthorize("isAuthenticated()")
public class TransferController {
<<<<<<< HEAD

    private TransferDao transferDao;
=======
>>>>>>> 324765135d6229d74dbdbaac819ae3f4b40315e6

    private TransferDao transferDao;

<<<<<<< HEAD

=======
    public TransferController(JdbcTransferDao transferDao) {
        this.transferDao = transferDao;
    }
>>>>>>> 324765135d6229d74dbdbaac819ae3f4b40315e6

    @PostMapping(value = "transfers")
    public Transfer create(@RequestBody Transfer transfer) {
       return transferDao.create(transfer);
    }
}
