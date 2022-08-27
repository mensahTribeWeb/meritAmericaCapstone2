package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.JdbcAccountDao;
import com.techelevator.tenmo.dao.JdbcTransferTypeDao;
import com.techelevator.tenmo.dao.TransferTypeDao;
import com.techelevator.tenmo.model.TransferType;
import org.springframework.web.bind.annotation.*;

@RestController
public class TransferTypeController {
    private TransferTypeDao dao;

    public TransferTypeController(JdbcTransferTypeDao dao) {
        this.dao = dao;
    }

    @GetMapping(value = "transfers/type/")
    public TransferType findByType(@RequestParam String type_like) {
        return dao.getByType(type_like);

    }

    @GetMapping(value = "transfers/type/{id}")
    public TransferType findById(@PathVariable int id) {
        return dao.getById(id);

    }
}
