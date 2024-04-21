USE spring6;
CREATE TABLE t_act(
	actno VARCHAR(255) NOT NULL PRIMARY KEY COMMENT"账号",
	balance DOUBLE(8,2) COMMENT"余额"
);

INSERT INTO t_act(actno, balance)
VALUES
("act-001", 50000),
("act-002", 0);

SELECT * FROM t_act; 

UPDATE t_act SET balance = 100000 WHERE actno = "act-001";

DELETE FROM t_act WHERE actno = "act-003";
DELETE FROM t_act WHERE actno = "act-004";
DELETE FROM t_act WHERE actno = "act-005";