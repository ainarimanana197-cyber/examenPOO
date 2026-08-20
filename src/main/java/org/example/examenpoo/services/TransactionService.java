package org.example.examenpoo.services;

import org.example.examenpoo.models.Transaction;
import org.example.examenpoo.models.TransactionType;
import org.example.examenpoo.repositories.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public List<Transaction> getTransactionsByType(String type) {
        TransactionType transactionType = TransactionType.valueOf(type.toUpperCase());
        return transactionRepository.findByType(transactionType);
    }

    public List<Transaction> getTransactionsByAccountId(String accountId) {
        return transactionRepository.findByAccountId(accountId);
    }
}