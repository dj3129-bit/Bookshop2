-- DROP TABLE IF EXISTS book;

CREATE TABLE IF NOT EXISTS book (
    code INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(32),
    publisher VARCHAR(32),
    price INT,
    pub_date DATE
);

-- DROP TABLE IF EXISTS customer;

CREATE TABLE IF NOT EXISTS customer (
    custid INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(32),
    address VARCHAR(32),
    phone VARCHAR(16)
);

-- DROP TABLE IF EXISTS orders;

CREATE TABLE IF NOT EXISTS orders (
    orderid INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    custid INT,
    bookid INT,
    saleprice INT,
    orderdate DATE
);

CREATE TABLE IF NOT EXISTS member (
    id VARCHAR(32) NOT NULL PRIMARY KEY,
    password VARCHAR(32),
    name VARCHAR(32),
    tel VARCHAR(32)
);