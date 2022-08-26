package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.JdbcAccountDao;
import com.techelevator.tenmo.dao.JdbcTransferTypeDao;
import com.techelevator.tenmo.dao.TransferTypeDao;
import com.techelevator.tenmo.model.TransferType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
