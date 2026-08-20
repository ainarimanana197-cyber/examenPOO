package org.example.examenpoo.services;

import org.example.examenpoo.models.Transaction;
import org.example.examenpoo.models.TransactionRequest;
import org.example.examenpoo.models.TransactionType;
import org.example.examenpoo.repositories.TransactionRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

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

    public Transaction createTransaction(TransactionRequest request) {
        Transaction transaction = new Transaction(
                UUID.randomUUID().toString(),
                Instant.now(),
                request.transactionType(),
                request.amount(),
                request.reason(),
                request.accountId()
        );

        transactionRepository.save(transaction);
        return transaction;
    }
}