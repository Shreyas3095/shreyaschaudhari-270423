INSERT INTO customer (id, first_name, last_name) VALUES (1, 'Alan', 'Smith');
INSERT INTO customer (id, first_name, last_name) VALUES (2, 'Joe', 'Turing');
INSERT INTO customer (id, first_name, last_name) VALUES (3, 'John', 'Smith');
INSERT INTO customer (id, first_name, last_name) VALUES (4, 'Kathy', 'Sierra');
INSERT INTO mobilenumber (id, mobile_number, customer_id) VALUES 
(1, '9111111111', (SELECT id FROM customer WHERE first_name = 'Alan' and last_name = 'Smith')),
(2, '9876565210', (SELECT id FROM customer WHERE first_name = 'Alan' and last_name = 'Smith')),
(3, '9865413218', (SELECT id FROM customer WHERE first_name = 'Alan' and last_name = 'Smith')),
(4, '9111111112', (SELECT id FROM customer WHERE first_name = 'Joe' and last_name = 'Turing')),
(5, '9876578911', (SELECT id FROM customer WHERE first_name = 'Joe' and last_name = 'Turing')),
(6, '9876514515', (SELECT id FROM customer WHERE first_name = 'Joe' and last_name = 'Turing')),
(7, '9111111113', (SELECT id FROM customer WHERE first_name = 'John' and last_name = 'Smith')),
(8, '9876632510', (SELECT id FROM customer WHERE first_name = 'John' and last_name = 'Smith')),
(9, '9154543210', (SELECT id FROM customer WHERE first_name = 'John' and last_name = 'Smith')),
(10, '9111111114', (SELECT id FROM customer WHERE first_name = 'Kathy' and last_name = 'Sierra')),
(11, '9876546590', (SELECT id FROM customer WHERE first_name = 'Kathy' and last_name = 'Sierra')),
(12, '9876543748', (SELECT id FROM customer WHERE first_name = 'Kathy' and last_name = 'Sierra')),
(13, '9876540123', (SELECT id FROM customer WHERE first_name = 'Alan' and last_name = 'Smith'));
