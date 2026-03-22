package com.app.dao.impl;

import com.app.dao.TransactionDAO;
import com.app.model.Transaction;
import com.app.util.DbConnection; // Using your connection utility!

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TransactionDAOImpl implements TransactionDAO {

    @Override
    public boolean addTransaction(Transaction transaction) {
        // Skipping transaction_id and transaction_datetime so MySQL can auto-generate them
        String sql = "INSERT INTO tbltransactions (procedure_id, user_id, service_id, medicine_id, quantity, total_amount, is_paid) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DbConnection.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, transaction.getProcedureId());
            ps.setInt(2, transaction.getUserId());
            ps.setInt(3, transaction.getServiceId());
            ps.setInt(4, transaction.getMedicineId());
            ps.setInt(5, transaction.getQuantity());
            ps.setInt(6, transaction.getTotalAmount());
            ps.setInt(7, transaction.getIsPaid());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("Error adding transaction: " + e.getMessage());
        }
        return false;
    }

    @Override
    public List<Transaction> getAllTransactions() {
        List<Transaction> transactionList = new ArrayList<>();
        String sql = "SELECT * FROM tbltransactions";

        try (Connection conn = DbConnection.connect();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Transaction transaction = new Transaction();

                transaction.setTransactionId(rs.getInt("transaction_id"));
                transaction.setProcedureId(rs.getInt("procedure_id")); // or process_id if unchanged in DB
                transaction.setUserId(rs.getInt("user_id"));
                transaction.setServiceId(rs.getInt("service_id"));
                transaction.setMedicineId(rs.getInt("medicine_id"));
                transaction.setQuantity(rs.getInt("quantity"));
                transaction.setTotalAmount(rs.getInt("total_amount"));
                transaction.setIsPaid(rs.getInt("is_paid"));

                // Read the datetime from the database as a String
                transaction.setTransactionDateTime(rs.getString("transaction_datetime"));

                transactionList.add(transaction);
            }
        } catch (Exception e) {
            System.out.println("Error retrieving transactions: " + e.getMessage());
        }
        return transactionList;
    }

    @Override
    public Transaction getTransactionById(int transactionId) {
        return null; // TODO: Implement later
    }

    @Override
    public boolean updateTransaction(Transaction transaction) {
        return false; // TODO: Implement later
    }

    @Override
    public boolean deleteTransaction(int transactionId) {
        return false; // TODO: Implement later
    }
}