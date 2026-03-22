package com.app.service.impl;

import com.app.dao.TransactionDAO;
import com.app.dao.impl.TransactionDAOImpl;
import com.app.model.Transaction;
import com.app.service.TransactionService;

import java.util.List;

public class TransactionServiceImpl implements TransactionService {

    private TransactionDAO transactionDAO = new TransactionDAOImpl();

    @Override
    public boolean addTransaction(Transaction transaction) {
        // Validation 1: Must link to a valid Procedure, User, and Service
        if (transaction.getProcedureId() <= 0) {
            System.out.println("Validation Error: Transaction must be linked to a valid Procedure ID.");
            return false;
        }
        if (transaction.getUserId() <= 0) {
            System.out.println("Validation Error: Transaction must be linked to a valid User ID.");
            return false;
        }
        if (transaction.getServiceId() <= 0) {
            System.out.println("Validation Error: Transaction must be linked to a valid Service ID.");
            return false;
        }

        // Validation 2: Medicine ID can be 0 (no medicine), but not negative
        if (transaction.getMedicineId() < 0) {
            System.out.println("Validation Error: Medicine ID cannot be negative.");
            return false;
        }

        // Validation 3: Financials cannot be negative
        if (transaction.getQuantity() < 0) {
            System.out.println("Validation Error: Quantity cannot be negative.");
            return false;
        }
        if (transaction.getTotalAmount() < 0) {
            System.out.println("Validation Error: Total amount cannot be negative.");
            return false;
        }

        // Validation 4: Payment status must strictly be 0 (Pending) or 1 (Paid)
        if (transaction.getIsPaid() != 0 && transaction.getIsPaid() != 1) {
            System.out.println("Validation Error: Payment status must be 0 or 1.");
            return false;
        }

        return transactionDAO.addTransaction(transaction);
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return transactionDAO.getAllTransactions();
    }

    @Override
    public Transaction getTransactionById(int transactionId) {
        if (transactionId <= 0) {
            System.out.println("Validation Error: Invalid Transaction ID.");
            return null;
        }
        return transactionDAO.getTransactionById(transactionId);
    }

    @Override
    public boolean updateTransaction(Transaction transaction) {
        if (transaction.getTransactionId() <= 0) {
            System.out.println("Validation Error: Cannot update. Invalid Transaction ID.");
            return false;
        }

        if (transaction.getTotalAmount() < 0) {
            System.out.println("Validation Error: Total amount cannot be updated to a negative number.");
            return false;
        }

        if (transaction.getIsPaid() != 0 && transaction.getIsPaid() != 1) {
            System.out.println("Validation Error: Payment status must be 0 or 1.");
            return false;
        }

        return transactionDAO.updateTransaction(transaction);
    }

    @Override
    public boolean deleteTransaction(int transactionId) {
        if (transactionId <= 0) {
            System.out.println("Validation Error: Invalid Transaction ID provided for deletion.");
            return false;
        }
        return transactionDAO.deleteTransaction(transactionId);
    }
}