--DROP TABLE IF EXISTS book;
INSERT INTO member(id, password, name, tel) VALUES ('kopo', '1234', '사용자1', '01011112222');
INSERT INTO member(id, password, name, tel) VALUES ('admin', '5678', '사용자2', '01011112223');
INSERT INTO member(id, password, name, tel) VALUES ('kopo1', '3333', '사용자3', '01011112221');
INSERT INTO member(id, password, name, tel) VALUES ('kopo2', '1111', '사용자4', '01011112224');
INSERT INTO member(id, password, name, tel) VALUES ('kopo3', '2222', '사용자5', '01011112225');
INSERT INTO member(id, password, name, tel) VALUES ('kopo4', '4444', '사용자6', '01011112226');
INSERT INTO member(id, password, name, tel) VALUES ('kopo5', '5555', '사용자7', '01011112227');

INSERT INTO book (title, publisher, price, pub_date) VALUES ('도서명 1', '출판사 1', 1000, NOW());
INSERT INTO book (title, publisher, price, pub_date) VALUES ('도서명 2', '출판사 2', 2000, NOW());
INSERT INTO book (title, publisher, price, pub_date) VALUES ('도서명 3', '출판사 3', 3000, NOW());
INSERT INTO book (title, publisher, price, pub_date) VALUES ('도서명 4', '출판사 4', 4000, NOW());
INSERT INTO book (title, publisher, price, pub_date) VALUES ('도서명 5', '출판사 5', 5000, NOW());

