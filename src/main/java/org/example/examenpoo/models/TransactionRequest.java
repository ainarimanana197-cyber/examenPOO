package org.example.examenpoo.models;

import java.math.BigDecimal;

public record TransactionRequest(
        TransactionType transactionType,
        BigDecimal amount,
        String reason,
        String accountId
) {}