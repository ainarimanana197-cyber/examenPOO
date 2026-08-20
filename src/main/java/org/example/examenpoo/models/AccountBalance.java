package org.example.examenpoo.models;

import java.math.BigDecimal;

public record AccountBalance(
        String accountId,
        BigDecimal balance
) {}
