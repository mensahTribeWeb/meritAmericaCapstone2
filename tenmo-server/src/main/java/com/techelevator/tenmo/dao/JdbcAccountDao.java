package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JdbcAccountDao implements AccountDao{

    private JdbcTemplate jdbcTemplate;

    public JdbcAccountDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Account findAccountByUserId(int id) {
        Account account = null;
        String sql = "SELECT account_id, user_id, balance from account where user_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql,id);
        if(results.next()) {
            account = mapRowToAccount(results);
        }
        return account;
    }

    @Override
    public void update(int id, Account account) {
        String sql = "UPDATE account set balance = ? WHERE user_id = ?;";
        jdbcTemplate.update(sql,account.getBalance(),id);

    }

    @Override
    public Account findByUsername(String name) {
        Account account = null;
        String sql = "SELECT a.account_id, a.user_id, a.balance FROM account AS a " +
                "JOIN tenmo_user u ON u.user_id = a.user_id " +
                "WHERE u.username ILIKE ?;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql,name);
        if(result.next()) {
            account = mapRowToAccount(result);
        }
        return account;
    }

    private Account mapRowToAccount(SqlRowSet results) {
        Account account = new Account();
        account.setId(results.getLong("account_id"));
        account.setUserId(results.getInt("user_id"));
        account.setBalance(results.getDouble("balance"));
        return account;
    }
}
