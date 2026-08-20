postgres=# CREATE DATABASE POO;
CREATE DATABASE
postgres=# CREATE TYPE AccountType AS ENUM ('STANDARD', 'PREMIUM', 'GOLD');
CREATE TYPE
    postgres=# CREATE TYPE TransactionType AS ENUM ('IN', 'OUT');
CREATE TYPE
    postgres=# CREATE TABLE Account
postgres-# (
postgres(# id VARCHAR(36) PRIMARY KEY,
postgres(# account_type AccountType NOT NULL
postgres(# );
CREATE TABLE
postgres=# CREATE TABLE Transaction
postgres-# (
postgres(# id VARCHAR(36) PRIMARY KEY,
postgres(# created_at TIMESTAMP NOT NULL DEFAULT now(),
postgres(# transaction_type TransactionType NOT NULL,
postgres(# amount DECIMAL(19, 4) NOT NULL,
postgres(# reason VARCHAR(255),
postgres(# account_id VARCHAR(36) NOT NULL,
postgres(# FOREIGN KEY (account_id) REFERENCES Account(id)
postgres(# );
CREATE TABLE