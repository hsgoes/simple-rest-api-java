DROP DATABASE expensetrackerdb;
DROP USER root;
CREATE USER root WITH password 'password';
CREATE DATABASE expensetrackerdb WITH template=template0 owner=root;
\c expensetrackerdb;
ALTER DEFAULT PRIVILEGES GRANT ALL ON tables TO root;
ALTER DEFAULT PRIVILEGES GRANT ALL ON sequences TO root;

CREATE TABLE et_users(
user_id INTEGER PRIMARY KEY NOT NULL,
first_name VARCHAR(20) NOT NULL,
last_name VARCHAR(25) NOT NULL,
email VARCHAR(50) NOT NULL,
password TEXT NOT NULL
);

CREATE TABLE et_categories(
category_id INTEGER PRIMARY KEY NOT NULL,
user_id INTEGER NOT NULL,
title VARCHAR(30) NOT NULL,
description VARCHAR(50) NOT NULL
);

ALTER TABLE et_categories ADD CONSTRAINT cat_users_fk
FOREIGN KEY (user_id) REFERENCES et_users(user_id);

CREATE TABLE et_transactions(
transactions_id INTEGER PRIMARY KEY NOT NULL,
category_id INTEGER NOT NULL,
user_id INTEGER NOT NULL,
amount NUMERIC(10,2) NOT NULL,
note VARCHAR(50) NOT NULL,
transactions_date BIGINT NOT NULL
);

ALTER TABLE et_transactions ADD CONSTRAINT trans_cat_users_fk
FOREIGN KEY (category_id) REFERENCES et_transactions(category_id);

ALTER TABLE et_transactions ADD CONSTRAINT trans_cat_users_fk
FOREIGN KEY (user_id) REFERENCES et_users(user_id);

CREATE SEQUENCE et_users_seq INCREMENT 1 START 1;
CREATE SEQUENCE et_categories_seq INCREMENT 1 START 1;
CREATE SEQUENCE et_transactions_seq INCREMENT 1 START 1000;

