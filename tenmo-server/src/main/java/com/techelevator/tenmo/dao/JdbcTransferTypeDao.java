package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.TransferType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JdbcTransferTypeDao implements  TransferTypeDao{

    private JdbcTemplate jdbcTemplate;

    public JdbcTransferTypeDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public TransferType getByType(String type_like) {
        TransferType transferType = null;
        String sql = "SELECT transfer_type_id, transfer_type_desc FROM transfer_type " +
                "WHERE transfer_type_desc ILIKE ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql,type_like);
        if(results.next()) {
            transferType = mapRowToTransferType(results);
        }
        return transferType;
    }

    @Override
    public TransferType getById(int id) {
        TransferType transferType = null;
        String sql = "SELECT transfer_type_id, transfer_type_desc FROM transfer_type " +
                "WHERE transfer_type_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql,id);
        if(results.next()) {
            transferType = mapRowToTransferType(results);
        }
        return transferType;
    }

    private TransferType mapRowToTransferType(SqlRowSet sql){
        TransferType transferType =  new TransferType();
        transferType.setTransferTypeId(sql.getInt("transfer_type_id"));
        transferType.setTransferTypeDesc(sql.getString("transfer_type_desc"));
        return transferType;

    }
}
