package com.app.service.impl;

import com.app.dao.TransactionDAO;
import com.app.dao.impl.TransactionDAOImpl;
import com.app.model.Transaction;
import com.app.service.TransactionService;

import java.util.List;

public class TransactionServiceImpl implements TransactionService {

    // The Service owns a copy of the DAO to talk to the database
    private TransactionDAO transactionDAO = new TransactionDAOImpl();

    @Override
    public boolean addTransaction(Transaction transaction) {
        // BUSINESS LOGIC & VALIDATION

        // Rule 1: Must be linked to a valid medical Procedure/Process
        if (transaction.getProcessID() <= 0) {
            System.out.println("Validation Error: Transaction must be linked to a valid Process ID.");
            return false;
        }

        // Rule 2: Must know who is paying
        if (transaction.getUserID() <= 0) {
            System.out.println("Validation Error: Transaction must be linked to a valid User ID.");
            return false;
        }

        // Rule 3: Must know what Service they are paying for
        if (transaction.getServiceID() <= 0) {
            System.out.println("Validation Error: Transaction must be linked to a valid Service ID.");
            return false;
        }

        // Rule 4: Medicine ID can be 0 (if no medicine was bought), but cannot be negative
        if (transaction.getMedicineID() < 0) {
            System.out.println("Validation Error: Medicine ID cannot be negative.");
            return false;
        }

        // Rule 5: Quantity cannot be negative
        if (transaction.getQuantity() < 0) {
            System.out.println("Validation Error: Quantity cannot be negative.");
            return false;
        }

        // Rule 6: Total Amount cannot be negative
        if (transaction.getTotalAmount() < 0) {
            System.out.println("Validation Error: Total amount cannot be a negative number.");
            return false;
        }

        // Rule 7: isPaid is usually 0 (unpaid) or 1 (paid). We make sure it isn't some random number.
        if (transaction.getIsPaid() < 0 || transaction.getIsPaid() > 1) {
            System.out.println("Validation Error: Payment status must be 0 (Unpaid) or 1 (Paid).");
            return false;
        }

        // If all rules pass, execute the DAO to save the money!
        return transactionDAO.addTransaction(transaction);
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return transactionDAO.getAllTransactions();
    }

    @Override
    public Transaction getTransactionById(int id) {
        if (id <= 0) {
            System.out.println("Validation Error: Invalid Transaction ID.");
            return null;
        }
        return transactionDAO.getTransactionById(id);
    }

    @Override
    public boolean updateTransaction(Transaction transaction) {
        // Validation: We need a valid ID to know which receipt to update
        if (transaction.getTransactionID() <= 0) {
            System.out.println("Validation Error: Cannot update. Invalid Transaction ID.");
            return false;
        }

        // Re-run the critical money validations so someone doesn't accidentally update a bill to negative pesos!
        if (transaction.getTotalAmount() < 0) {
            System.out.println("Validation Error: Total amount cannot be updated to a negative number.");
            return false;
        }

        if (transaction.getIsPaid() < 0 || transaction.getIsPaid() > 1) {
            System.out.println("Validation Error: Payment status must be 0 (Unpaid) or 1 (Paid).");
            return false;
        }

        return transactionDAO.updateTransaction(transaction);
    }

    @Override
    public boolean deleteTransaction(int id) {
        // Validation: Prevent accidental deletions of clinic financial records
        if (id <= 0) {
            System.out.println("Validation Error: Invalid Transaction ID provided for deletion.");
            return false;
        }
        return transactionDAO.deleteTransaction(id);
    }
}