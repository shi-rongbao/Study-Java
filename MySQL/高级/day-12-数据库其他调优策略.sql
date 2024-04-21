# 分析表
USE atguigudb3;
CREATE TABLE `user1`(
	`id` INT NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(255) DEFAULT NULL,
	`age` INT DEFAULT NULL,
	`sex` VARCHAR(255) DEFAULT NULL,
	PRIMARY KEY(`id`),
	KEY `idx_name` (`name`) USING BTREE
)ENGINE = INNODB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8mb3;

SET GLOBAL log_bin_trust_function_creators = 1;

DELIMITER $
CREATE FUNCTION rand_num(from_num INT, to_num INT) RETURNS INT(11)
BEGIN
DECLARE i INT DEFAULT 0;
SET i = FLOOR(from_num + RAND() * (to_num - from_num + 1));
RETURN i;
END $
DELIMITER ;

DELIMITER $
CREATE PROCEDURE insert_user(max_num INT)
BEGIN
DECLARE i INT DEFAULT 0;
SET autocommit = 0;
REPEAT 
SET i = i + 1;
INSERT INTO `user1` (`name`, age, sex)
VALUES("shirongbao", rand_num(1, 20), "male");
UNTIL i = max_num
END REPEAT;
COMMIT;
END $
DELIMITER ;

CALL insert_user(1000);

SELECT COUNT(*) FROM user1;

SHOW INDEX FROM user1;

SELECT * FROM user1;

UPDATE user1 SET `name` = 'shirongbao3' WHERE id = 3;

# 分析表
ANALYZE TABLE user1;

# 检查表
CHECK TABLE user1;

# 优化表
CREATE TABLE t1(id INT, `name` VARCHAR(15)) ENGINE = MYISAM;
CREATE TABLE t2(id INT, `name` VARCHAR(15)) ENGINE = INNODB;

# Table is already up to date
OPTIMIZE TABLE t1;

# Table does not support optimize, doing recreate + analyze instead
OPTIMIZE TABLE t2;