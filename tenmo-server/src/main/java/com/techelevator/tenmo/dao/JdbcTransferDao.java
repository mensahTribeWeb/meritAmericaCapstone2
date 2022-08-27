package com.techelevator.tenmo.dao;
        import com.techelevator.tenmo.model.Transfer;
        import org.springframework.jdbc.core.JdbcTemplate;
        import org.springframework.jdbc.support.rowset.SqlRowSet;
        import org.springframework.stereotype.Component;

        import java.util.ArrayList;
        import java.util.List;

@Component
public class JdbcTransferDao implements TransferDao{

    //properties
    private JdbcTemplate jdbcTemplate;


    //constructor

    public JdbcTransferDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;

    }




    @Override
    public Transfer getTransferDetails(Long id) {
        return null;
    }


    @Override
    public List<Transfer> listAll() {
        return null;
    }

    @Override
    public void sendTransfer(Transfer transfer) {
        String sql = "INSERT INTO transfer (transfer_type_id, transfer_status_id, account_from, account_to, amount)\n"+
                    "VALUES(2, 2, (SELECT account_id FROM accounts WHERE user_id = ?), ?);";
        jdbcTemplate.update(sql, transfer.getAccount_from(), transfer.getAccount_to(), transfer.getTransfer_amount());
    }

    @Override
    public List<Transfer> getTransferHistory(Long id) {
        List<Transfer> transferHistory = new ArrayList<>();
        String sql = "SELECT transfer_id, transfer_type_id, transfer_status_id, account_from, account_to, amount\n"+
                "FROM transfer\n" +
                "JOIN accounts ON transfer.account_from = account.account_id" +
                "JOIN users ON account.user_id = users.user_id\n"+
                "WHERE user.id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
        while (results.next()) {
           Transfer transfer = mapRowToTransfer(results);
           transferHistory.add(transfer);
        }
        return transferHistory;

    }

    @Override
    public Transfer getTransferDetails(int id) {
        Transfer fetchedTransfer = null;
        String sql = "SELECT transfer_id, transfer_type_id, transfer_status_id, account_from, account_to, amount "+
                        "FROM transfer " +
                        "WHERE transfer_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql,id);
        if (results.next()) {
            fetchedTransfer = mapRowToTransfer(results);
        }
        return fetchedTransfer;
    }

    @Override
    public Transfer create(Transfer transfer) {
        String sql = "INSERT INTO transfer (transfer_type_id, transfer_status_id, account_from, account_to, amount) " +
                "values(?,?,?,?,?) returning transfer_id;";
        int transferId = jdbcTemplate.queryForObject(sql, int.class,
                    transfer.getTransfer_type_id(),
                    transfer.getTransfer_status_id(),
                    transfer.getAccount_from(),
                    transfer.getAccount_to(),
                    transfer.getTransfer_amount());
        return getTransferDetails(transferId);
    }

    private Transfer mapRowToTransfer(SqlRowSet results) {
        Transfer transfer = new Transfer();
        transfer.setTransfer_id(results.getInt("transfer_id"));
        transfer.setTransfer_type_id(results.getInt("transfer_type_id"));
        transfer.setTransfer_status_id(results.getInt("transfer_status_id"));
        transfer.setAccount_from(results.getInt("account_from"));
        transfer.setAccount_to(results.getInt("account_to"));
        transfer.setTransfer_amount(results.getDouble("amount"));
        return transfer;
    }
//Test
//        SELECT transfer_id, transfer_type_id, transfer_status_id, account_from, account_to, amount
//        FROM transfer
//        JOIN account ON transfer.account_from = account.account_id
//        JOIN tenmo_user ON account.user_id = tenmo_user.user_id
//        WHERE tenmo_user.user_id = 1001;

}
