package com.techelevator.tenmo.model;

public class Account {
    private Long id;
    private int userId;
    private double balance;

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

    public void transferTo(Account anotherAccount, double transferAmount) {
        this.withdraw(transferAmount);
        anotherAccount.deposit(transferAmount);
    }

    public void deposit(double amount) {
        this.balance += amount;
    }

    public void withdraw(double amount) {
        this.balance -= amount;
    }


}
