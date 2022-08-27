package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.TransferStatus;
import com.techelevator.tenmo.model.TransferType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JdbcTransferStatusDao implements TransferStatusDao {
    private JdbcTemplate jdbcTemplate;

    public JdbcTransferStatusDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public TransferStatus getByStatus(String status_like) {
        TransferStatus transferStatus = null;
        String sql = "SELECT transfer_status_id, transfer_status_desc FROM transfer_status " +
                "WHERE transfer_status_desc ILIKE ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql,status_like);
        if(results.next()) {
            transferStatus  = mapRowToTransferStatus(results);
        }
        return transferStatus ;
    }

    @Override
    public TransferStatus getById(int id) {
        TransferStatus transferStatus = null;
        String sql = "SELECT transfer_status_id, transfer_status_desc FROM transfer_status " +
                "WHERE transfer_status_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql,id);
        if(results.next()) {
            transferStatus = mapRowToTransferStatus(results);
        }
        return transferStatus;
    }

    private TransferStatus mapRowToTransferStatus(SqlRowSet sql){
        TransferStatus transferStatus =  new TransferStatus();
        transferStatus.setTransferStatusId(sql.getLong("transfer_status_id"));
        transferStatus.setTransferStatusDesc(sql.getString("transfer_status_desc"));
        return transferStatus;

    }
}