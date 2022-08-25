package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.JdbcAccountDao;
import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.util.ForbiddenAccessException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@PreAuthorize("isAuthenticated()")
public class AccountController {

    private AccountDao accountDao;

    public AccountController(JdbcAccountDao jdbcAccountDao) {
        this.accountDao = jdbcAccountDao;
    }

    @GetMapping(value = "{id}/account")
    public Account getAccountByUserId(@PathVariable int id, Principal principal) throws ForbiddenAccessException {
        if(accountDao.findByUsername(principal.getName()).getUserId() == id) {
            return accountDao.findAccountByUserId(id);
        }
        else {
            throw new ForbiddenAccessException();
        }
    }

    @PutMapping(value = "{id}/account")
    public void update(@PathVariable int id, @RequestBody Account account) {
        accountDao.update(id,account);
    }
}
