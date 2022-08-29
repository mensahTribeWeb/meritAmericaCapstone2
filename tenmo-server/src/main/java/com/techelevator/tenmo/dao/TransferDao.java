package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;

import java.util.List;

public interface TransferDao {
    List<Transfer> listAll();

    Transfer findById(int id);

    Transfer create(Transfer transfer);

    List<Transfer> listAllWithAccountId(int id);
}
