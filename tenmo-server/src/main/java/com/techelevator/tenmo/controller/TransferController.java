package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.TransferDao;
import com.techelevator.tenmo.dao.TransferTypeDao;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.TransferType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "transfers")
@PreAuthorize("isAuthenticated()")
public class TransferController {

    private TransferDao transferDao;

    public TransferController(TransferDao transferDao) {
        this.transferDao = transferDao;
    }

    @GetMapping
    public List<Transfer> listAll() {
        return transferDao.listAll();
    }

    @GetMapping(value = "/account/{id}")
    public List<Transfer> listAllWithFromId(@PathVariable int id) {
        return transferDao.listAllWithAccountId(id);
    }

    @GetMapping(value = "/{id}")
    public Transfer findById(@PathVariable int id) {
        return transferDao.findById(id);
    }

    @PostMapping
    public Transfer create(@Valid @RequestBody Transfer transfer) {
        return transferDao.create(transfer);
    }


}
