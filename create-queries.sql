--
-- File generated with SQLiteStudio v3.1.1 on Mo Nov 12 10:19:02 2018
--
-- Text encoding used: System
--
PRAGMA foreign_keys = off;
BEGIN TRANSACTION;

-- Table: accounts
CREATE TABLE accounts (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, name VARCHAR (255) NOT NULL, address TEXT NOT NULL, email VARCHAR (255) UNIQUE NOT NULL, password VARCHAR (255) NOT NULL, credit_card VARCHAR (255) UNIQUE);

-- Table: colors
CREATE TABLE colors (id INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE NOT NULL, color VARCHAR (255) UNIQUE NOT NULL);

-- Table: order_status
CREATE TABLE order_status (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, status VARCHAR NOT NULL UNIQUE);

-- Table: order_stock
CREATE TABLE order_stock (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, order_id INTEGER REFERENCES orders (id) NOT NULL, stock_id INTEGER NOT NULL REFERENCES stock (id), amount INTEGER NOT NULL DEFAULT (1));

-- Table: orders
CREATE TABLE orders (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, account_id REFERENCES accounts (id), order_date DATETIME DEFAULT (CURRENT_TIMESTAMP) NOT NULL, status_id INTEGER REFERENCES order_status (id));

-- Table: product_types
CREATE TABLE product_types (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, type VARCHAR NOT NULL UNIQUE);

-- Table: products
CREATE TABLE products (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE, name VARCHAR (255) NOT NULL UNIQUE, description TEXT, price DOUBLE NOT NULL, product_type_id INTEGER REFERENCES product_types (id) NOT NULL);

-- Table: sizes
CREATE TABLE sizes (id INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE NOT NULL, size VARCHAR (5) UNIQUE NOT NULL);

-- Table: stock
CREATE TABLE stock (id INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE NOT NULL, product_id INTEGER NOT NULL REFERENCES products (id), amount INTEGER NOT NULL, color_id INTEGER NOT NULL REFERENCES colors (id), size_id NOT NULL REFERENCES sizes (id));

-- View: stock_view
CREATE VIEW stock_view AS SELECT s.id, p.name, color, size, amount from stock s JOIN colors c ON s.color_id = c.id JOIN products p on p.id = s.product_id JOIN sizes si ON s.size_id = si.id;

COMMIT TRANSACTION;
PRAGMA foreign_keys = on;
