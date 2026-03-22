package com.app.dao;

import com.app.model.Transaction;
import java.util.List;

public interface TransactionDAO {
    boolean addTransaction(Transaction transaction);
    List<Transaction> getAllTransactions();
    Transaction getTransactionById(int transactionId);
    boolean updateTransaction(Transaction transaction);
    boolean deleteTransaction(int transactionId);
}