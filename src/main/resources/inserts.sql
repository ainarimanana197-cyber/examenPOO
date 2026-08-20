postgres=# INSERT INTO Account (id, account_type) VALUES
postgres-# ('a1111111-1111-1111-1111-111111111111', 'STANDARD'),
postgres-# ('a2222222-2222-2222-2222-222222222222', 'PREMIUM'),
postgres-# ('a3333333-3333-3333-3333-333333333333', 'GOLD');
INSERT 0 3
postgres=# INSERT INTO Transactions (id, created_at, transaction_type, amount, reason, account_id) VALUES
postgres-# ('t1111111-1111-1111-1111-111111111111', now(), 'IN',  1500.0000, 'Dépôt initial',        'a1111111-1111-1111-1111-111111111111'),
postgres-# ('t2222222-2222-2222-2222-222222222222', now(), 'OUT',  250.5000, 'Achat en ligne',       'a1111111-1111-1111-1111-111111111111'),
postgres-# ('t3333333-3333-3333-3333-333333333333', now(), 'IN',  3000.0000, 'Virement salaire',     'a2222222-2222-2222-2222-222222222222'),
postgres-# ('t4444444-4444-4444-4444-444444444444', now(), 'OUT',  120.7500, 'Facture électricité',  'a2222222-2222-2222-2222-222222222222'),
postgres-# ('t5555555-5555-5555-5555-555555555555', now(), 'IN',  10000.0000, 'Prime annuelle',      'a3333333-3333-3333-3333-333333333333');
INSERT 0 5