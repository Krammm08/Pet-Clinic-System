package com.app.dao;

import java.util.List;
import com.app.model.Transaction;
import com.app.exception.DatabaseException;

public interface TransactionDAO {

    // Create transaction
    boolean insertTransaction(Transaction transaction) throws DatabaseException;

    // Get transactions by user
    List<Transaction> getTransactionsByUserId(int userId) throws DatabaseException;

    // Get all transactions (admin)
    List<Transaction> getAllTransactions() throws DatabaseException;

    // Get transaction by ID
    Transaction getTransactionById(int transactionId) throws DatabaseException;

    // Mark as paid
    boolean markAsPaid(int transactionId) throws DatabaseException;
}
