package org.example.examenpoo.repositories;

import org.example.examenpoo.models.Transaction;
import org.example.examenpoo.models.TransactionType;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class TransactionRepository {

    private final String url = "jdbc:postgresql://localhost:5432/POO";
    private final String user = "postgres";
    private final String password = "12345678";

    public List<Transaction> findByType(TransactionType type) {

        List<Transaction> transactions = new ArrayList<>();
        String sql = "SELECT id, created_at, transaction_type, amount, reason, account_id " +
                "FROM transaction WHERE transaction_type = ?";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, type.name());

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    transactions.add(mapRow(rs));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return transactions;
    }

    public List<Transaction> findByAccountId(String accountId) {
        List<Transaction> transactions = new ArrayList<>();
        String sql = "SELECT id, created_at, transaction_type, amount, reason, account_id " +
                "FROM transaction WHERE account_id = ?";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, accountId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    transactions.add(mapRow(rs));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return transactions;
    }

    private Transaction mapRow(ResultSet rs) throws SQLException {
        return new Transaction(
                rs.getString("id"),
                rs.getTimestamp("created_at").toInstant(),
                TransactionType.valueOf(rs.getString("transaction_type")),
                rs.getBigDecimal("amount"),
                rs.getString("reason"),
                rs.getString("account_id")
        );
    }
    public void save(Transaction transaction) {
        String sql = "INSERT INTO transaction (id, created_at, transaction_type, amount, reason, account_id) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, transaction.id());
            stmt.setTimestamp(2, Timestamp.from(transaction.createdAt()));
            stmt.setString(3, transaction.transactionType().name());
            stmt.setBigDecimal(4, transaction.amount());
            stmt.setString(5, transaction.reason());
            stmt.setString(6, transaction.accountId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public BigDecimal calculateBalance(String accountId) {
        String sql = "SELECT " +
                "COALESCE(SUM(CASE WHEN transaction_type = 'IN' THEN amount ELSE 0 END), 0) - " +
                "COALESCE(SUM(CASE WHEN transaction_type = 'OUT' THEN amount ELSE 0 END), 0) AS balance " +
                "FROM transaction WHERE account_id = ?";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, accountId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getBigDecimal("balance");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return BigDecimal.ZERO;
    }
}