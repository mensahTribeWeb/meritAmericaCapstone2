package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.JdbcTransferStatusDao;
import com.techelevator.tenmo.dao.JdbcTransferTypeDao;
import com.techelevator.tenmo.dao.TransferStatusDao;
import com.techelevator.tenmo.dao.TransferTypeDao;
import com.techelevator.tenmo.model.TransferStatus;
import com.techelevator.tenmo.model.TransferType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransferStatusController {
    private TransferStatusDao dao;

    public TransferStatusController(JdbcTransferStatusDao dao) {
        this.dao = dao;
    }

    @GetMapping(value = "transfers/status/")
    public TransferStatus findByStatus(@RequestParam String status_like) {
        return dao.getByStatus(status_like);

    }

    @GetMapping(value = "transfers/status/{id}")
    public TransferStatus findById(@PathVariable int id) {
        return dao.getById(id);

    }
}


