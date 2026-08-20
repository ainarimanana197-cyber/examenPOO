package org.example.examenpoo.repositories;

import org.example.examenpoo.models.Transaction;
import org.example.examenpoo.models.TransactionType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class TransactionRepository {

    private final String url = "jdbc:postgresql://localhost:5432/tp_db";
    private final String user = "postgres";
    private final String password = "postgres";

    public List<Transaction> findByType(TransactionType type) {
        List<Transaction> transactions = new ArrayList<>();
        String sql = "SELECT id, created_at, transaction_type, amount, reason, account_id " +
                "FROM transaction WHERE transaction_type = ?";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, type.name());

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Transaction t = new Transaction(
                            rs.getString("id"),
                            rs.getTimestamp("created_at").toInstant(),
                            TransactionType.valueOf(rs.getString("transaction_type")),
                            rs.getBigDecimal("amount"),
                            rs.getString("reason"),
                            rs.getString("account_id")
                    );
                    transactions.add(t);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return transactions;
    }
}