CREATE DATABASE IF NOT EXISTS ARCH_DB;
USE ARCH_DB;

CREATE TABLE ORDERS(
    ORDERS_ID INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,                        
    NAME VARCHAR(80),
    CONTENT TEXT NOT NULL,
    REQUESTED_DATETIME DATETIME NOT NULL,
    PARSED_DATE DATETIME NOT NULL);

CREATE TABLE EXECUTIONS(
    EXECUTIONS_ID INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
    EXECUTED_ORDER INTEGER NOT NULL, 
    EXECUTION_DATE DATETIME NOT NULL,
     CONSTRAINT FOREIGN KEY(EXECUTED_ORDER) REFERENCES ORDERS(ORDERS_ID));


DESCRIBE ORDERS;
DESCRIBE EXECUTIONS;
