INSERT INTO users (id, name) VALUES (1, 'Alice');
INSERT INTO users (id, name) VALUES (2, 'Bob');
INSERT INTO users (id, name) VALUES (3, 'Charlie');

INSERT INTO expenses (id, description, amount, date) VALUES (1, 'Dinner', 120.00, '2026-03-01');
INSERT INTO expenses (id, description, amount, date) VALUES (2, 'Groceries', 45.50, '2026-03-02');

INSERT INTO expense_participants (expense_id, user_id) VALUES (1, 1);
INSERT INTO expense_participants (expense_id, user_id) VALUES (1, 2);
INSERT INTO expense_participants (expense_id, user_id) VALUES (1, 3);

INSERT INTO expense_participants (expense_id, user_id) VALUES (2, 1);
INSERT INTO expense_participants (expense_id, user_id) VALUES (2, 2);
