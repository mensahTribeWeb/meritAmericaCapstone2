package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.TransferDao;
import com.techelevator.tenmo.dao.TransferTypeDao;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.TransferType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "transfers")
public class TransferController {

    private TransferDao transferDao;

<<<<<<< HEAD






    public TransferController(JdbcTransferDao transferDao) {
=======
    public TransferController(TransferDao transferDao) {
>>>>>>> ccc0e071dd854b52c583f6988ed7a8d84415a546
        this.transferDao = transferDao;
    }

    @GetMapping
    public List<Transfer> listAll() {
        return transferDao.listAll();
    }

    @GetMapping(value = "/account/{id}")
    public List<Transfer> listAllWithFromId(@PathVariable int id) {
        return transferDao.listAllWithFromId(id);
    }

    @GetMapping(value = "/{id}")
    public Transfer findById(@PathVariable int id) {
        return transferDao.findById(id);
    }

    @PostMapping
    public Transfer create(@RequestBody Transfer transfer) {
        return transferDao.create(transfer);
    }


}
