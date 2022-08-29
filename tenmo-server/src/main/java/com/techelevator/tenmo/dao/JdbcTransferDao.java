package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcTransferDao implements TransferDao{

    private JdbcTemplate jdbcTemplate;

    public JdbcTransferDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Transfer> listAll() {
        List<Transfer> transfers = new ArrayList<>();
        String sql = "SELECT transfer_id, " +
                "transfer_type_id, " +
                "transfer_status_id, " +
                "account_from, " +
                "account_to, " +
                "amount FROM transfer;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while(results.next()) {
            transfers.add(mapRowToTransfer(results));
        }
        return transfers;
    }

    @Override
    public Transfer findById(int id) {
        Transfer transfer = null;
        String sql = "SELECT transfer_id, " +
                "transfer_type_id, " +
                "transfer_status_id, " +
                "account_from, " +
                "account_to, " +
                "amount FROM transfer WHERE transfer_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql,id);
        if(results.next()) {
            transfer = mapRowToTransfer(results);
        }
        return transfer;
    }

    @Override
    public Transfer create(Transfer transfer) {
        String sql = "INSERT INTO transfer (transfer_type_id, transfer_status_id, account_from, account_to, amount) " +
                "values(?,?,?,?,?) returning transfer_id;";
        int transferId = jdbcTemplate.queryForObject(sql, int.class,
                transfer.getTransferTypeId(),
                transfer.getTransferStatusId(),
                transfer.getFromAccountId(),
                transfer.getToAccountId(),
                transfer.getTransferAmount());
        return findById(transferId);
    }

    @Override
    public List<Transfer> listAllWithAccountId(int id) {
        List<Transfer> transfers = new ArrayList<>();
        String sql = "SELECT transfer_id, " +
                "transfer_type_id, " +
                "transfer_status_id, " +
                "account_from, " +
                "account_to, " +
                "amount FROM transfer WHERE account_from = ? OR account_to = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql,id,id);
        while(results.next()) {
            transfers.add(mapRowToTransfer(results));
        }
        return transfers;
    }


    private Transfer mapRowToTransfer(SqlRowSet results) {
        Transfer transfer = new Transfer();
        transfer.setId(results.getInt("transfer_id"));
        transfer.setTransferTypeId(results.getInt("transfer_type_id"));
        transfer.setTransferStatusId(results.getInt("transfer_status_id"));
        transfer.setFromAccountId(results.getInt("account_from"));
        transfer.setToAccountId(results.getInt("account_to"));
        transfer.setTransferAmount(results.getDouble("amount"));
        return transfer;
    }

}
