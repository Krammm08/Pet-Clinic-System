package com.app.service;

import com.app.model.Transaction;
import java.util.List;

public interface TransactionService {
    boolean addTransaction(Transaction transaction);
    List<Transaction> getAllTransactions();
    Transaction getTransactionById(int id);
    boolean updateTransaction(Transaction transaction);
    boolean deleteTransaction(int id);
}