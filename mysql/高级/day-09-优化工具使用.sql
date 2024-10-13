USE atguigudb1;

SHOW TABLES;

CREATE TABLE `student`(
	`id` INT(11) NOT NULL AUTO_INCREMENT, 
	`stuno` INT NOT NULL, 
	`name` VARCHAR(20) DEFAULT NULL, 
	`age`  INT(3) DEFAULT NULL, 
	`classId` INT(11) DEFAULT NULL, 
	PRIMARY KEY(`id`) 
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DELIMITER $

CREATE PROCEDURE insert_stu1(START INT, max_num INT)

    BEGIN
    DECLARE i INT DEFAULT 0;
    SET autocommit = 0;
    REPEAT
	SET i = i + 1;
	INSERT INTO student(stuno, NAME, age, classId) VALUES
	((START + i ), rand_string(6), rand_num(10, 100), rand_num(10, 1000));
	UNTIL i = max_num
    END REPEAT;
    COMMIT;
    END $
    
DELIMITER ;

CALL insert_stu1(100001, 4000000);


SELECT * FROM student LIMIT 100;

# 
EXPLAIN SELECT * FROM student_info;

SELECT * FROM student_info LIMIT 10;

EXPLAIN DELETE FROM student_info WHERE id = 2;

EXPLAIN SELECT 1;

# 创建表
CREATE TABLE s1(
	id INT AUTO_INCREMENT,
	key1 VARCHAR(100),
	key2 INT,
	key3 VARCHAR(100),
	key_part1 VARCHAR(100),
	key_part2 VARCHAR(100),
	key_part3 VARCHAR(100),
	common_field VARCHAR(100),
	PRIMARY KEY (id),
	INDEX idx_key1(key1),
	UNIQUE INDEX idx_key2(key2),
	INDEX idx_key3(key3),
	INDEX idx_key_part(key_part1, key_part2, key_part3)
) ENGINE = INNODB CHARSET=utf8;

CREATE TABLE s2(
	id INT AUTO_INCREMENT,
	key1 VARCHAR(100),
	key2 INT,
	key3 VARCHAR(100),
	key_part1 VARCHAR(100),
	key_part2 VARCHAR(100),
	key_part3 VARCHAR(100),
	common_field VARCHAR(100),
	PRIMARY KEY (id),
	INDEX idx_key1(key1),
	UNIQUE INDEX idx_key2(key2),
	INDEX idx_key3(key3),
	INDEX idx_key_part(key_part1, key_part2, key_part3)
)ENGINE = INNODB CHARSET = utf8;

DROP FUNCTION rand_string1;

# 模拟数据
DELIMITER $
CREATE FUNCTION rand_string1(n INT)
	RETURNS VARCHAR(255)	
BEGIN 
	DECLARE chars_str VARCHAR(100) DEFAULT 'abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ';
	DECLARE return_str VARCHAR(255) DEFAULT '';
	DECLARE i INT DEFAULT 0;
	WHILE i < n DO
		SET return_str = CONCAT(return_str, SUBSTRING(chars_str, FLOOR(1+RAND()*52), 1));
		SET i = i + 1;
	END WHILE;
	RETURN return_str;
END $
DELIMITER ;
	
SHOW VARIABLES LIKE '%log_bin_trust_function%';
SET GLOBAL log_bin_trust_function_creators = ON;

# 创建存储过程
DELIMITER $
CREATE PROCEDURE insert_s1(IN min_num INT(10), IN max_num  INT(10)) 
BEGIN
	DECLARE i INT DEFAULT 0;
	SET autocommit = 0;
	REPEAT 
	SET i = i + 1;
	INSERT INTO s1
	VALUES
	((min_num + i), 
	rand_string1(6), 
	(min_num + 30 * i + 5), 
	rand_string1(6), 
	rand_string1(10), 
	rand_string1(5), 
	rand_string1(10), 
	rand_string1(10));
	UNTIL i = max_num
	END REPEAT;
	COMMIT;
END $
DELIMITER ;

DELIMITER $
CREATE PROCEDURE insert_s2(IN min_num INT(10), IN max_num  INT(10)) 
BEGIN
	DECLARE i INT DEFAULT 0;
	SET autocommit = 0;
	REPEAT 
	SET i = i + 1;
	INSERT INTO s2
	VALUES
	((min_num + i), 
	rand_string1(6), 
	(min_num + 30 * i + 5), 
	rand_string1(6), 
	rand_string1(10), 
	rand_string1(5), 
	rand_string1(10), 
	rand_string1(10));
	UNTIL i = max_num
	END REPEAT;
	COMMIT;
END $
DELIMITER ;

# 调用存储过程插入模拟数据
CALL insert_s1(10001, 10000);

CALL insert_s2(10001, 10000);

# 查询数据是否插入成功
SELECT COUNT(*) FROM s1;
SELECT COUNT(*) FROM s2;