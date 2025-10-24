DROP TABLE IF EXISTS book;

CREATE TABLE IF NOT EXISTS book (
    code INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(32),
    publisher VARCHAR(32),
    price INT
    pub_date DATE
);

INSERT INTO book (bookname, publisher, price, pub_date) VALUES ('도서명 1', '출판사 1', 1000, NOW());
INSERT INTO book (bookname, publisher, price, pub_date) VALUES ('도서명 2', '출판사 2', 2000, NOW());
INSERT INTO book (bookname, publisher, price, pub_date) VALUES ('도서명 3', '출판사 3', 3000, NOW());
INSERT INTO book (bookname, publisher, price, pub_date) VALUES ('도서명 4', '출판사 4', 4000, NOW());
INSERT INTO book (bookname, publisher, price, pub_date) VALUES ('도서명 5', '출판사 5', 5000, NOW());

DROP TABLE IF EXISTS customer;

CREATE TABLE IF NOT EXISTS customer (
    custid INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(32),
    address VARCHAR(32),
    phone VARCHAR(16)
);

DROP TABLE IF EXISTS orders;

CREATE TABLE IF NOT EXISTS orders (
    orderid INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    custid INT,
    bookid INT,
    saleprice INT,
    orderdate DATE
);