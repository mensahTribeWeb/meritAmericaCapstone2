package com.techelevator.tenmo.model;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

public class Account {

    private Long id;
    @NotNull(message = "Account must have user id.")
    private int userId;
    @DecimalMin(value = "0.01", message = "Account must be updated with a minimum of $0.01.")
    private double balance;

    public Account() {
    }

    public Account(Long id, int userId, double balance) {
        this.id = id;
        this.userId = userId;
        this.balance = balance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
