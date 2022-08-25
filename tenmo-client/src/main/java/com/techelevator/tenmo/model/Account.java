package com.techelevator.tenmo.model;

public class Account {
    private int id;
    private int userId;
    private double balance;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
