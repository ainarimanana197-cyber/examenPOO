package org.example.examenpoo.controllers;


import org.example.examenpoo.models.AccountBalance;
import org.example.examenpoo.models.Transaction;
import org.example.examenpoo.models.TransactionRequest;
import org.example.examenpoo.services.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    public List<Transaction> getTransaction(@RequestParam String type) {
        return transactionService.getTransactionsByType(type);
    }
    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@RequestBody TransactionRequest request) {
        Transaction created = transactionService.createTransaction(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
    @GetMapping("/{id}/balance")
    public AccountBalance getAccountBalance(@PathVariable String id) {
        return transactionService.getAccountBalance(id);
    }
}
