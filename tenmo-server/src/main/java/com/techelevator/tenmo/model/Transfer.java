package com.techelevator.tenmo.model;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;


public class Transfer {

    private int id;

    @Range(min = 1, max = 2, message = "A transferTypeId may only be 1 or 2")
    private int transferTypeId;
    @Range(min = 1, max = 3, message = "A transferTypeStatus may only be 1, 2 or 3")
    private int transferStatusId;
    @NotNull(message = "A transfer must originate from a valid user.")
    private int fromAccountId;
    @NotNull(message = "A transfer must be sent to a valid user.")
    private int toAccountId;
    @DecimalMin(value = "0.01", message = "A transfer must have a minimum value of 0.01")
    private double transferAmount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTransferTypeId() {
        return transferTypeId;
    }

    public void setTransferTypeId(int transferTypeId) {
        this.transferTypeId = transferTypeId;
    }

    public int getTransferStatusId() {
        return transferStatusId;
    }

    public void setTransferStatusId(int transferStatusId) {
        this.transferStatusId = transferStatusId;
    }

    public int getFromAccountId() {
        return fromAccountId;
    }

    public void setFromAccountId(int fromAccountId) {
        this.fromAccountId = fromAccountId;
    }

    public int getToAccountId() {
        return toAccountId;
    }

    public void setToAccountId(int toAccountId) {
        this.toAccountId = toAccountId;
    }

    public double getTransferAmount() {
        return transferAmount;
    }

    public void setTransferAmount(double transferAmount) {
        this.transferAmount = transferAmount;
    }
}
