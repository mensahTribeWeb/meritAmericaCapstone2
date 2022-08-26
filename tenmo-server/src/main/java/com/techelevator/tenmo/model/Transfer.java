package com.techelevator.tenmo.model;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

public class Transfer {
    //properties
    private int transfer_id;
    private int transfer_type_id;
    private int transfer_status_id;
    @NotBlank(message = "A transfer must originate from a valid user.")
    private int account_from;
    @NotBlank(message = "A transfer must be sent to a valid user.")
    private int account_to;
    @DecimalMin(value = "0.01", message = "A transfer must have a minimum value of 0.01")
    private BigDecimal transfer_amount;
    
    //Constructors
    public Transfer() {
    }

    public Transfer(int transfer_id,
                    int transfer_type_id,
                    int transfer_status_id,
                    int account_from,
                    int account_to,
                    BigDecimal transfer_amount) {
        this.transfer_id = transfer_id;
        this.transfer_type_id = transfer_type_id;
        this.transfer_status_id = transfer_status_id;
        this.account_from = account_from;
        this.account_to = account_to;
        this.transfer_amount = transfer_amount;
    }
    //Getters

    public int getTransfer_id() {
        return transfer_id;
    }

    public int getTransfer_type_id() {
        return transfer_type_id;
    }

    public int getTransfer_status_id() {
        return transfer_status_id;
    }

    public int getAccount_from() {
        return account_from;
    }

    public int getAccount_to() {
        return account_to;
    }

    public BigDecimal getTransfer_amount() {
        return transfer_amount;
    }

    //Setters

    public void setTransfer_id(int transfer_id) {
        this.transfer_id = transfer_id;
    }

    public void setTransfer_type_id(int transfer_type_id) {
        this.transfer_type_id = transfer_type_id;
    }

    public void setTransfer_status_id(int transfer_status_id) {
        this.transfer_status_id = transfer_status_id;
    }

    public void setAccount_from(int account_from) {
        this.account_from = account_from;
    }

    public void setAccount_to(int account_to) {
        this.account_to = account_to;
    }

    public void setTransfer_amount(BigDecimal transfer_amount) {
        this.transfer_amount = transfer_amount;
    }
}
