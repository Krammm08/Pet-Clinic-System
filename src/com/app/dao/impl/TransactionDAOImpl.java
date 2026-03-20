package com.app.dao.impl;

import com.app.dao.TransactionDAO;
import com.app.model.Transaction;
import com.app.util.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TransactionDAOImpl implements TransactionDAO{
    @Override
    public boolean addtransaction(Transaction transaction){
        String sql = "INSERT INTO tbltransactions (transaction_id, process_id, user_id, service_id, medicine_id, quantity, total_amount, is_paid, transaction_datetime) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try(Connection connection = DbConnection.connect();
            PreparedStatement prep = connection.prepareStatement(sql)){

            prep.setInt(1, transaction.getQuantity());
            prep.setInt(2, transaction.getTotalAmount());
            prep.setInt(3, transaction.getIsPaid());
            prep.setTimestamp(5, transaction.getTransactionDateTime());

            return prep.executeUpdate() > 0;
        } catch (Exception e){
            System.out.println("Error adding transaction: " + e.getMessage());
        }
        return false;
    }

    @Override
    public List<Transaction> getAllTransactions(){
        List<Transaction> transactionList = new ArrayList<>();
        String sql = "SELECT * FROM tblmedicines";

        try(Connection connection = DbConnection.connect();
            PreparedStatement prep = connection.prepareStatement(sql);
            ResultSet result = prep.executeQuery()){

            while(result.next()){
                Transaction transaction = new Transaction();

                transaction.setTransactionID(result.getInt("transaction_id"));
                transaction.setTotalAmount(result.getInt("total_amount"));
                transaction.setIsPaid(result.getInt("is_paid"));
                transaction.setTransactionDateTime(result.getTimestamp("transaction_datetime"));

                transactionList.add(transaction);
            }
        }catch (Exception e){
            System.out.println("Error retrieving transactions; " + e.getMessage());
        }
        return transactionList;
    }

    @Override
    public Transaction getTransactionById(int id){return null;}

    @Override
    public boolean updateTransaction(Transaction transaction){return false;}

    @Override
    public boolean deleteTransaction(int id){return false;}
}
