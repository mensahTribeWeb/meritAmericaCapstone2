package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;

public interface AccountDao {

    Account findAccountByUserId(int id);
    void update(int id, Account account);

}
