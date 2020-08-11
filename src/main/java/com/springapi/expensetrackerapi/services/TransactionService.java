package com.springapi.expensetrackerapi.services;

import com.springapi.expensetrackerapi.domain.Transaction;
import com.springapi.expensetrackerapi.exception.EtBadRequestException;
import com.springapi.expensetrackerapi.exception.EtResourceNotFoundException;

import java.util.List;

public interface TransactionService {

    List<Transaction> fetchAllTransactions(Integer userId, Integer categoryId);

    Transaction fetchTransactionById(Integer userId, Integer categoryId,
                                     Integer transactionId) throws EtResourceNotFoundException;

    Transaction addTransaction(Integer userId, Integer categoryId, Double amount, String note,
                               Long transactionDate) throws EtBadRequestException;

    void updateTransaction(Integer userId, Integer categoryId, Integer transactionId,
                           Transaction transaction) throws EtBadRequestException;

    void removeTransaction(Integer userId, Integer categoryId,
                           Integer transactionId) throws EtResourceNotFoundException;
}
