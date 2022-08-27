
package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.TransferStatus;
import com.techelevator.tenmo.model.TransferType;

public interface TransferStatusDao {
    TransferStatus getByStatus(String status_like);

    TransferStatus getById(int id);
}