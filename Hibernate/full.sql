BEGIN;
DROP TABLE IF EXISTS products CASCADE;
CREATE TABLE products (id bigint PRIMARY KEY AUTO_INCREMENT, title VARCHAR(255), cost int);
INSERT INTO products (title, cost)
VALUES ('Bananas', 50), ('Apple', 35), ('Kiwi', 40), ('Avocado',43), ('Cucumber',15);
COMMIT;