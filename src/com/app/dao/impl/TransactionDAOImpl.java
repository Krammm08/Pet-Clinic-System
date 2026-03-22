package com.app.dao.impl;

import com.app.dao.TransactionDAO;
import com.app.model.Transaction;
import com.app.util.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TransactionDAOImpl implements TransactionDAO {

    @Override
    public boolean addTransaction(Transaction transaction) {
        String sql = "INSERT INTO tbltransactions (procedure_id, user_id, service_id, medicine_id, quantity, total_amount, is_paid) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DbConnection.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, transaction.getProcedureId());
            ps.setInt(2, transaction.getUserId());
            ps.setInt(3, transaction.getServiceId());
            ps.setInt(4, transaction.getMedicineId());
            ps.setInt(5, transaction.getQuantity());
            ps.setDouble(6, transaction.getTotalAmount());
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
                Transaction transaction = mapResultSetToTransaction(rs);
                transactionList.add(transaction);
            }
        } catch (Exception e) {
            System.out.println("Error retrieving transactions: " + e.getMessage());
        }
        return transactionList;
    }

    @Override
    public Transaction getTransactionById(int transactionId) {
        return null;
    }

    // FIXED: Added the missing method required by the Interface
    @Override
    public List<Transaction> getUserTransactions(int userId) {
        List<Transaction> transactions = new ArrayList<>();
        // Ensure these names match your XAMPP columns exactly!
        String sql = "SELECT * FROM tbltransactions WHERE user_id = ?";

        try (Connection conn = DbConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Transaction t = new Transaction();
                t.setTransactionId(rs.getInt("transaction_id"));
                t.setTotalAmount(rs.getDouble("total_amount"));
                t.setIsPaid(rs.getInt("is_paid"));
                // ... set other fields ...
                transactions.add(t);
            }
        } catch (SQLException e) {
            System.out.println("\t[DEBUG] DAO Error: " + e.getMessage());
        }
        return transactions;
    }
    // Helper method to avoid repeating the "rs.get..." code twice
    private Transaction mapResultSetToTransaction(ResultSet rs) throws java.sql.SQLException {
        Transaction t = new Transaction();
        t.setTransactionId(rs.getInt("transaction_id"));
        t.setProcedureId(rs.getInt("procedure_id"));
        t.setUserId(rs.getInt("user_id"));
        t.setServiceId(rs.getInt("service_id"));
        t.setMedicineId(rs.getInt("medicine_id"));
        t.setQuantity(rs.getInt("quantity"));
        t.setTotalAmount(rs.getInt("total_amount"));
        t.setIsPaid(rs.getInt("is_paid"));
        t.setTransactionDateTime(rs.getString("transaction_datetime"));
        return t;
    }

    @Override
    public boolean updateTransaction(Transaction transaction) {
        return false; // TODO: Implement later
    }

    @Override
    public boolean deleteTransaction(int transactionId) {
        return false; // TODO: Implement later
    }

    @Override
    public boolean payTransaction(int transactionId) {
        // Sets is_paid to 1
        String sql = "UPDATE tbltransactions SET is_paid = 1 WHERE transaction_id = ?";
        try (Connection conn = DbConnection.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, transactionId);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("Error processing payment: " + e.getMessage());
        }
        return false;
    }
}