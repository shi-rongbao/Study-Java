# 14-事物日志

USE atguigudb3;

CREATE TABLE test_load(
	a INT,
	b CHAR(80)
)ENGINE = INNODB;

# 创建存储过程，添加数据
DELIMITER $

CREATE PROCEDURE p_load(`count` INT UNSIGNED)
BEGIN
DECLARE s INT UNSIGNED DEFAULT 1;
DECLARE c CHAR(80) DEFAULT REPEAT('a', 80);
WHILE s <= `count` DO
INSERT INTO test_load SELECT NULL, c;
COMMIT;
SET s = s + 1;
END WHILE;
END $

DELIMITER;

# 测试1：
# 设置并查看：innodb_flush_log_at_trx_commit;

SHOW VARIABLES LIKE '%innodb_flush_log_at_trx_commit%';

SET GLOBAL innodb_flush_log_at_trx_commit = 1;

# 调用存储过程

CALL p_load(30000);  # 1 min 12 sec

#  测试2：
TRUNCATE TABLE test_load;

SET GLOBAL innodb_flush_log_at_trx_commit = 0;

CALL p_load(30000);   # 37.193 sec

# 测试3：
TRUNCATE TABLE test_load;

SET GLOBAL innodb_flush_log_at_trx_commit = 2;

CALL p_load(30000);   # 43.044 sec

