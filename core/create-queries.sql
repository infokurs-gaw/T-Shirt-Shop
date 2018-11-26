BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS `stock` (
	`id`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,
	`product_id`	INTEGER NOT NULL,
	`amount`	INTEGER NOT NULL,
	`color_id`	INTEGER NOT NULL,
	`size_id`	TEXT NOT NULL,
	FOREIGN KEY(`color_id`) REFERENCES `colors`(`id`),
	FOREIGN KEY(`size_id`) REFERENCES `sizes`(`id`),
	FOREIGN KEY(`product_id`) REFERENCES `products`(`id`)
);
INSERT INTO `stock` VALUES (1,1,10,1,1),
 (2,1,5,1,4);
CREATE TABLE IF NOT EXISTS `sizes` (
	`id`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,
	`size`	VARCHAR ( 5 ) NOT NULL UNIQUE
);
INSERT INTO `sizes` VALUES (1,'XS'),
 (2,'S'),
 (3,'M'),
 (4,'L'),
 (5,'XL'),
 (6,'XXL');
CREATE TABLE IF NOT EXISTS `products` (
	`id`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,
	`name`	VARCHAR ( 255 ) NOT NULL UNIQUE,
	`description`	TEXT,
	`price`	DOUBLE NOT NULL,
	`product_type_id`	INTEGER NOT NULL,
	FOREIGN KEY(`product_type_id`) REFERENCES `product_types`(`id`)
);
INSERT INTO `products` VALUES (1,'Infokurs T-Shirt',NULL,19.95,1);
CREATE TABLE IF NOT EXISTS `product_types` (
	`id`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	`type`	VARCHAR NOT NULL UNIQUE
);
INSERT INTO `product_types` VALUES (1,'T-Shirt'),
 (2,'Pullover'),
 (3,'Hose');
CREATE TABLE IF NOT EXISTS `orders` (
	`id`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	`account_id`	TEXT,
	`order_date`	DATETIME NOT NULL DEFAULT (CURRENT_TIMESTAMP),
	`status_id`	INTEGER NOT NULL DEFAULT (1),
	FOREIGN KEY(`status_id`) REFERENCES `order_status`(`id`),
	FOREIGN KEY(`account_id`) REFERENCES `accounts`(`id`)
);
INSERT INTO `orders` VALUES (1,1,'2018-10-29 09:04:58',1);
CREATE TABLE IF NOT EXISTS `order_stock` (
	`id`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	`order_id`	INTEGER NOT NULL,
	`stock_id`	INTEGER NOT NULL,
	`amount`	INTEGER NOT NULL DEFAULT (1),
	FOREIGN KEY(`stock_id`) REFERENCES `stock`(`id`),
	FOREIGN KEY(`order_id`) REFERENCES `orders`(`id`)
);
INSERT INTO `order_stock` VALUES (1,1,1,1),
 (2,1,2,2);
CREATE TABLE IF NOT EXISTS `order_status` (
	`id`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	`status`	VARCHAR NOT NULL UNIQUE
);
INSERT INTO `order_status` VALUES (1,'AUFGEBENEN'),
 (2,'BEARBEITET'),
 (3,'VERSANDT'),
 (4,'ERHALTEN'),
 (5,'ABGESCHLOSSEN');
CREATE TABLE IF NOT EXISTS `event_log` (
	`id`	INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE,
	`account_id`	INTEGER,
	`event`	VARCHAR ( 255 ) NOT NULL,
	`timestamp`	DATETIME DEFAULT (CURRENT_TIMESTAMP),
	FOREIGN KEY(`account_id`) REFERENCES `accounts`(`id`)
);
INSERT INTO `event_log` VALUES (1,1,'LOGIN','2018-11-17 09:51:01'),
 (2,1,'LOGIN','2018-11-17 09:51:55'),
 (3,1,'LOGOUT','2018-11-17 09:52:41');
CREATE TABLE IF NOT EXISTS `colors` (
	`id`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,
	`color`	VARCHAR ( 255 ) NOT NULL UNIQUE
);
INSERT INTO `colors` VALUES (1,'schwarz'),
 (2,'rot');
CREATE TABLE IF NOT EXISTS `accounts` (
	`id`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	`name`	VARCHAR ( 255 ) NOT NULL,
	`address`	TEXT NOT NULL,
	`email`	VARCHAR ( 255 ) NOT NULL UNIQUE,
	`password`	VARCHAR ( 255 ) NOT NULL,
	`credit_card`	VARCHAR ( 255 ) UNIQUE,
	`last_viewed_product_id` INTEGER--,
	--FOREIGN KEY (`last_viewed_product_id`) REFERENCES `stock`(`id`)
);
INSERT INTO `accounts` VALUES (1,'Celina','Bla Bla','celina@gaw.de','bieeeeebg','2554738', 1);
CREATE VIEW stock_view AS SELECT s.id, p.name, color, size, amount from stock s JOIN colors c ON s.color_id = c.id JOIN products p on p.id = s.product_id JOIN sizes si ON s.size_id = si.id;
COMMIT;
