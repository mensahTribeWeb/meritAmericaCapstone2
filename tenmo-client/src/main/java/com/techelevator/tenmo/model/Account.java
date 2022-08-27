package com.techelevator.tenmo.model;

public class Account {
    private int id;
    private int userId;
    private double balance;

    public Account() {
    }

    public Account(int id, int userId, double balance) {
        this.id = id;
        this.userId = userId;
        this.balance = balance;
    }

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

    /**
     * Transfers the amount of funds specified to the target account
     * @param anotherAccount The account to be transferred to.
     * @param transferAmount The amount to be transferred
     * @throws IllegalArgumentException When anotherAccount is the current account, or if amount is not valid
     */
    public void transferTo(Account anotherAccount, double transferAmount) throws IllegalArgumentException {
        if(this.equals(anotherAccount)) {
            throw new IllegalArgumentException("Cannot transfer money to self.");
        }
        if(transferAmount <= 0) {
            throw new IllegalArgumentException("Transfer amount must be greater than 0.");
        }
        if(this.balance < transferAmount) {
            throw new IllegalArgumentException("Insufficient Funds.");
        }
        this.withdraw(transferAmount);
        anotherAccount.deposit(transferAmount);
    }

    /**
     * Deposits the specified amount into this account
     * @param amount The amount to be deposited
     */
    public void deposit(double amount) {
        this.balance += amount;
    }

    /**
     * Withdraws the specified amount from this account
     * @param amount The amount to be withdrawn
     */
    public void withdraw(double amount) {
        this.balance -= amount;
    }


}
