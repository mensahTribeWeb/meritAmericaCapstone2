package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.JdbcAccountDao;
import com.techelevator.tenmo.model.Account;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@PreAuthorize("isAuthenticated()")
public class AccountController {

    private AccountDao accountDao;

    public AccountController(JdbcAccountDao jdbcAccountDao) {
        this.accountDao = jdbcAccountDao;
    }

    @GetMapping(value = "{id}/account")
    public Account getAccountByUserId(@PathVariable int id) {
        return accountDao.findAccountByUserId(id);
    }

    @PutMapping(value = "{id}/account")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void update(@PathVariable int id, @Valid @RequestBody Account account) {
        accountDao.update(id, account);
    }
}
