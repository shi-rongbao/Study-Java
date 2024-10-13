# 10-索引优化与查询优化

# 1. 数据准备工作

CREATE DATABASE atguigudb2;
USE atguigudb2;

CREATE TABLE `class`(
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`className` VARCHAR(30) DEFAULT NULL,
	`address` VARCHAR(40) DEFAULT NULL,
	`monitor` INT NULL,
	PRIMARY KEY (`id`)
) ENGINE = INNODB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8;


CREATE TABLE `student`(
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`stuno` INT NOT NULL,
	`name` VARCHAR(20) DEFAULT NULL,
	`age` INT(3) DEFAULT NULL,
	`classId` INT(11) DEFAULT NULL,
	PRIMARY KEY(`id`)
) ENGINE = INNODB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8;

# 允许创建函数设置：
SHOW VARIABLES LIKE '%trust_function%';
SET GLOBAL log_bin_trust_function_creators=1;

DROP FUNCTION rand_string;

# 创建函数产生随机字符串
DELIMITER $
CREATE FUNCTION rand_string(n INT) RETURNS VARCHAR(255)
BEGIN
DECLARE chars_str VARCHAR(100) DEFAULT 'abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ';
DECLARE return_str VARCHAR(255) DEFAULT '';
DECLARE i INT DEFAULT 0;
WHILE i < n DO
SET return_str = CONCAT(return_str, SUBSTRING(chars_str, FLOOR(1+ RAND() * 52), 1));
SET i = i + 1;
END WHILE;
RETURN return_str;
END $
DELIMITER ;

# 创建函数产生多少到多少的随机编号
DELIMITER $
CREATE FUNCTION rand_num(from_num INT, to_num INT) RETURNS INT(11)
BEGIN
DECLARE i INT DEFAULT 0;
SET i = FLOOR(from_num + RAND() * (to_num - from_num + 1));
RETURN i;
END $
DELIMITER ;

# 创建存储过程往stu表中插入数据
DELIMITER $
CREATE PROCEDURE insert_stu (`start` INT, max_num INT)
BEGIN
DECLARE i INT DEFAULT 0;
SET autocommit = 0;
REPEAT SET i = i + 1;
INSERT INTO student(stuno, `name`, age, classId) VALUES
((`start` + i), rand_string(6), rand_num(1, 50), rand_num(1, 1000));
UNTIL i = max_num
END REPEAT;
COMMIT;
END $
DELIMITER ;

# 创建存储过程往class表添加随机数据
DELIMITER $
CREATE PROCEDURE `insert_class` (max_num INT)
BEGIN
DECLARE i INT DEFAULT 0;
SET autocommit = 0;
REPEAT 
SET i = i + 1;
INSERT INTO class(classname, address, monitor) VALUES
(rand_string(8), rand_string(10), rand_num(1, 100000));
UNTIL i = max_num
END REPEAT;
COMMIT;
END $
DELIMITER ;


# 调用存储过程添加数据
CALL insert_class(10000);

CALL insert_stu(100000, 500000);

SELECT COUNT(*) FROM class;
SELECT COUNT(*) FROM student;


DELIMITER $
CREATE PROCEDURE `proc_drop_index`(dbname VARCHAR(200), tablename VARCHAR(200))
BEGIN
	DECLARE done INT DEFAULT 0;
	DECLARE ct INT DEFAULT 0;
	DECLARE _index VARCHAR(200) DEFAULT '';
	DECLARE _cur CURSOR FOR SELECT index_name FROM information_schema.`STATISTICS` WHERE table_schema=dbname AND table_name=tablename
	AND seq_in_index = 1 AND index_name <>`primary`;
	DECLARE CONTINUE HANDLER FOR NOT FOUND SET done=2;
	OPEN _cur;
	FETCH _cur INTO _index;
	WHILE _index<>'' DO
		SET @str = CONCAT("drop index", _index, " on ", tablename);
		PREPARE sql_str FROM @str;
		EXECUTE sql_str;
		DEALLOCATE PREPARE sql_str;
		SET _index='';
		FETCH _cur INTO _index;
	END WHILE;
CLOSE _cur;
END $
DELIMITER ;

# 2. 索引失效案例
# ①全值匹配我最爱
EXPLAIN SELECT SQL_NO_CACHE * FROM student WHERE age = 30;  # type = all
EXPLAIN SELECT SQL_NO_CACHE * FROM student WHERE age = 30 AND classId = 4;
EXPLAIN SELECT SQL_NO_CACHE * FROM student WHERE age = 30 AND classId = 4 AND NAME = 'abcd';

SELECT SQL_NO_CACHE * FROM student WHERE age = 30 AND classId = 4 AND NAME = 'abcd';

CREATE INDEX idx_age ON student(age);  # 添加索引
CREATE INDEX idx_age_classid ON student(age, classId);  # 添加联合索引
CREATE INDEX idx_age_classid_name ON student(age, classId, `name`);  # 添加联合索引

# ②最佳左前缀法则
EXPLAIN SELECT * FROM student WHERE student.age=30 AND student.name = 'abcd';
EXPLAIN SELECT * FROM student WHERE student.classid=1 AND student.name = 'abcd';
EXPLAIN SELECT * FROM student WHERE classid = 4 AND student.age = 30 AND student.name = 'abcd';

# ③主键插入顺序
/**
要保证主键顺序递增插入，否则插入数据时，会导致底层存储结构顺序发生变更，从而增加耗时
建议让主键AUTO_INCREMENT
*/

# ④计算，函数，类型转换（自动或手动）导致索引失效
# 添加索引
ALTER TABLE student ADD INDEX idx_name (`name`);

# 这个语句比下面的更好一点
EXPLAIN SELECT * FROM student WHERE student.name LIKE 'abc%';
EXPLAIN SELECT * FROM student WHERE student.name LIKE '%abc';
EXPLAIN SELECT * FROM student WHERE LEFT(student.name, 3) = 'abc';

CREATE INDEX idx_sno ON student(stuno)
EXPLAIN SELECT id, stuno, `name` FROM student WHERE stuno + 1 = 900001;
EXPLAIN SELECT id, stuno, `name` FROM student WHERE stuno = 900000;

# ⑤类型转换导致索引失效 
# 类型自动转换
EXPLAIN SELECT * FROM student WHERE `name` = 123;

# ⑥范围条件右侧的列的索引失效
# 查看表中的索引
SHOW INDEX FROM student;
# 调用存储过程删除索引
CALL proc_drop_index('atguigudb2', 'student');
# 删除索引
DROP INDEX idx_name ON student;
DROP INDEX idx_sno ON student;

# 添加索引
CREATE INDEX idx_age_classId_name ON student(age, classId, `name`);

EXPLAIN SELECT * FROM student
WHERE student.age = 30 AND student.`classId` > 20 AND student.`name` = 'abc';

# 添加索引
CREATE INDEX idx_age_name_cid ON student(age, `name`, classId);

# ⑦不等于( !=  或者 <>) 索引失效
# 查看索引
SHOW INDEX FROM student;

# 添加索引
CREATE INDEX idx_name ON student(`name`);

# 查询
EXPLAIN SELECT * FROM student WHERE student.`name` <> 'abc';

# ⑧ IS NULL 可以使用索引， IS NOT NULL不可以使用索引
EXPLAIN SELECT * FROM student WHERE age IS NULL;

EXPLAIN SELECT * FROM student WHERE age IS NOT NULL;

# ⑨ like 以通配符%开头的索引失效
EXPLAIN SELECT * FROM student WHERE `name` LIKE 'abc%';
EXPLAIN SELECT * FROM student WHERE `name` LIKE '%abc';

# ⑩ OR 前后存在非索引的列，索引失效
# 查看索引
SHOW INDEX FROM student;
# 删除索引 
DROP INDEX idx_age_classId_name ON student;
DROP INDEX idx_age_name_cid ON student;
DROP INDEX idx_name ON student;

# 添加索引
CREATE INDEX idx_age ON student(age);
# 执行查询
EXPLAIN SELECT * FROM student WHERE age = 10 OR classid = 100;

# 添加索引
CREATE INDEX idx_cid ON student(classid);
# 执行查询
EXPLAIN SELECT * FROM student WHERE age = 10 OR classid = 100;

# ①① 数据库和表的字符集统一使用utf8mb4

/**
统一使utf8mb4(5.5.3版本以上支持)兼容性好，统一字符集可以避免由于字符集转换产生的乱码。不同的字符集进行比较前需要进行转换会造成索引失效。
*/

# 3. 关联查询优化

# 情况1： 左外连接
# 分类
CREATE TABLE IF NOT EXISTS `type`(
	`id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
	`card` INT(10) UNSIGNED NOT NULL,
	PRIMARY KEY (`id`)
);
# 图书
CREATE TABLE IF NOT EXISTS `book` (
	`bookid` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
	`card` INT (10) UNSIGNED NOT NULL,
	PRIMARY KEY (`bookid`)
);
# 向分类表中添加20条记录
INSERT INTO `type`(card) VALUES (FLOOR(1 + (RAND() * 20)));
INSERT INTO `type`(card) VALUES (FLOOR(1 + (RAND() * 20)));
INSERT INTO `type`(card) VALUES (FLOOR(1 + (RAND() * 20)));
INSERT INTO `type`(card) VALUES (FLOOR(1 + (RAND() * 20)));
INSERT INTO `type`(card) VALUES (FLOOR(1 + (RAND() * 20)));
INSERT INTO `type`(card) VALUES (FLOOR(1 + (RAND() * 20)));
INSERT INTO `type`(card) VALUES (FLOOR(1 + (RAND() * 20)));
INSERT INTO `type`(card) VALUES (FLOOR(1 + (RAND() * 20)));
INSERT INTO `type`(card) VALUES (FLOOR(1 + (RAND() * 20)));
INSERT INTO `type`(card) VALUES (FLOOR(1 + (RAND() * 20)));
INSERT INTO `type`(card) VALUES (FLOOR(1 + (RAND() * 20)));
INSERT INTO `type`(card) VALUES (FLOOR(1 + (RAND() * 20)));
INSERT INTO `type`(card) VALUES (FLOOR(1 + (RAND() * 20)));
INSERT INTO `type`(card) VALUES (FLOOR(1 + (RAND() * 20)));
INSERT INTO `type`(card) VALUES (FLOOR(1 + (RAND() * 20)));
INSERT INTO `type`(card) VALUES (FLOOR(1 + (RAND() * 20)));
INSERT INTO `type`(card) VALUES (FLOOR(1 + (RAND() * 20)));
INSERT INTO `type`(card) VALUES (FLOOR(1 + (RAND() * 20)));
INSERT INTO `type`(card) VALUES (FLOOR(1 + (RAND() * 20)));
INSERT INTO `type`(card) VALUES (FLOOR(1 + (RAND() * 20)));


INSERT INTO book(card) VALUES (FLOOR(1 + (RAND() * 20)));
INSERT INTO book(card) VALUES (FLOOR(1 + (RAND() * 20)));
INSERT INTO book(card) VALUES (FLOOR(1 + (RAND() * 20)));
INSERT INTO book(card) VALUES (FLOOR(1 + (RAND() * 20)));
INSERT INTO book(card) VALUES (FLOOR(1 + (RAND() * 20)));
INSERT INTO book(card) VALUES (FLOOR(1 + (RAND() * 20)));
INSERT INTO book(card) VALUES (FLOOR(1 + (RAND() * 20)));
INSERT INTO book(card) VALUES (FLOOR(1 + (RAND() * 20)));
INSERT INTO book(card) VALUES (FLOOR(1 + (RAND() * 20)));
INSERT INTO book(card) VALUES (FLOOR(1 + (RAND() * 20)));
INSERT INTO book(card) VALUES (FLOOR(1 + (RAND() * 20)));
INSERT INTO book(card) VALUES (FLOOR(1 + (RAND() * 20)));
INSERT INTO book(card) VALUES (FLOOR(1 + (RAND() * 20)));
INSERT INTO book(card) VALUES (FLOOR(1 + (RAND() * 20)));
INSERT INTO book(card) VALUES (FLOOR(1 + (RAND() * 20)));
INSERT INTO book(card) VALUES (FLOOR(1 + (RAND() * 20)));
INSERT INTO book(card) VALUES (FLOOR(1 + (RAND() * 20)));
INSERT INTO book(card) VALUES (FLOOR(1 + (RAND() * 20)));
INSERT INTO book(card) VALUES (FLOOR(1 + (RAND() * 20)));
INSERT INTO book(card) VALUES (FLOOR(1 + (RAND() * 20)));

SELECT * FROM `type`;
SELECT * FROM `book`;

EXPLAIN SELECT * FROM `type` LEFT JOIN book ON type.`card` = book.`card`;
# 添加索引
CREATE INDEX idx_card ON book(card);
CREATE INDEX idx_card ON `type`(card);

EXPLAIN SELECT * FROM `type` LEFT JOIN book ON type.`card` = book.`card`;

# 删除索引
DROP INDEX idx_card ON book;

EXPLAIN SELECT * FROM `type` LEFT JOIN book ON type.`card` = book.`card`;

# 查看所有的索引
SHOW INDEX FROM book;
SHOW INDEX FROM `type`;
# 删除索引
DROP INDEX idx_card ON `type`;


# 情况2： 内链接

EXPLAIN SELECT * FROM `type` INNER JOIN book ON type.`card` = book.`card`;

# 添加索引
CREATE INDEX idx_card ON book(card);
# 执行查询
EXPLAIN SELECT * FROM `type` INNER JOIN book ON type.`card` = book.`card`;

# 结论：对于内连接来讲，如果表的链接条件中只能有一个字段有索引，则有索引的字段所在的表会被作为被驱动表
# 结论：对于内连接来说，在两个表的链接条件都存在索引的情况下，会选择小表作为驱动表。 “小表驱动大表”


# JOIN的底层原理

CREATE TABLE a (f1 INT, f2 INT, INDEX(f1)) ENGINE = INNODB;
CREATE TABLE b (f1 INT, f2 INT) ENGINE = INNODB;

INSERT INTO a VALUES(1, 1), (2, 2), (3, 3), (4, 4), (5, 5), (6, 6);
INSERT INTO b VALUES(3, 3), (4, 4), (5, 5), (6, 6), (7, 7), (8, 8);

SELECT * FROM b;

# 测试1
EXPLAIN SELECT * FROM a LEFT JOIN b ON (a.`f1` = b.`f1`) WHERE (a.`f2` = b.`f2`);
# 测试2
EXPLAIN SELECT * FROM a LEFT JOIN b ON (a.`f1` = b.`f1`) AND (a.`f2` = b.`f2`);

# 测试3
EXPLAIN SELECT * FROM a JOIN b ON (a.`f1` = b.`f1`) WHERE (a.`f2` = b.`f2`);


# 4. 子查询的优化

SHOW INDEX FROM class;

# 创建班级表中班长的索引
CREATE INDEX idx_monitor ON class(monitor);

# 查询班长的信息
# 子查询
EXPLAIN SELECT * FROM student stu1
WHERE stu1.`stuno` IN (
SELECT monitor
FROM class c
WHERE monitor IS NOT NULL
);

# 多表查询
EXPLAIN SELECT stu1.* FROM student stu1 JOIN class c
ON stu1.`stuno` = c.`monitor`
WHERE c.`monitor` IS NOT NULL;

# 查询不为班长的学生信息
# 子查询
EXPLAIN SELECT a.*
FROM student a 
WHERE a.stuno NOT IN (
	SELECT monitor FROM class b
	WHERE monitor IS NOT NULL
);

# 转为外连接查询
EXPLAIN SELECT a.*
FROM student a LEFT OUTER JOIN class b
ON a.stuno = b.monitor
WHERE b.monitor IS NULL;

# 5. 排序优化

# 删除student和class表中的索引
SHOW INDEX FROM student;
DROP INDEX idx_age ON student;
DROP INDEX idx_cid ON student;

SHOW INDEX FROM class;
DROP INDEX idx_monitor ON class;

EXPLAIN SELECT * FROM student ORDER BY age, classid;

EXPLAIN SELECT * FROM student ORDER BY age, classid LIMIT 10;

# 添加索引
CREATE INDEX idx_age_cid_name ON student(age, classid, `name`);

# 不存在LIMIT，无法使用索引
# 查询字段需要回表，成本较高，优化器优化后不通过索引
EXPLAIN SELECT * FROM student ORDER BY age, classid;

# 只查询需要ORDER BY 的字段，因此不需要回表
EXPLAIN SELECT age, classid FROM student ORDER BY age, classid;
 
# 存在LIMIT，使用索引
EXPLAIN SELECT * FROM student ORDER BY age, classid LIMIT 10;

# 创建索引age, classid, stuno
SHOW INDEX FROM student;
DROP INDEX idx_age_cid_name ON student;
CREATE INDEX idx_age_cid_stuno ON student(age, classid, stuno);

# 以下哪些索引失效？
# 失效，不符合最佳左前缀法则
EXPLAIN SELECT * FROM student ORDER BY classid LIMIT 10;  

# 失效，不符合最佳左前缀法则
EXPLAIN SELECT * FROM student ORDER BY classid, `name` LIMIT 10;

# using index
EXPLAIN SELECT * FROM student ORDER BY age, classid, stuno LIMIT 10;

# using index
EXPLAIN SELECT * FROM student ORDER BY age, classid LIMIT 10;

# using index
EXPLAIN SELECT * FROM student ORDER BY age LIMIT 10;

# ORDER BY 时规则不一致，索引失效（顺序错，不索引；反方向，不索引）

# 失效，索引中age不是DESC排序的
EXPLAIN SELECT * FROM student ORDER BY age DESC, classid ASC LIMIT 10;

# 失效，索引中classid 与 `name` 都不是DESC排序的
EXPLAIN SELECT * FROM student ORDER BY classid DESC, `name` DESC LIMIT 10;

# 失效，索引中classid不是DESC排序的
EXPLAIN SELECT * FROM student ORDER BY age ASC, classid DESC LIMIT 10;

# 能使用索引，倒着遍历即可
EXPLAIN SELECT * FROM student ORDER BY age DESC, classid DESC LIMIT 10;

# 无过滤，不索引
# 可以使用索引，只用到了联合索引的的age也就是WHERE的条件
EXPLAIN SELECT * FROM student WHERE age = 45 ORDER BY classid;

# 可以使用索引，只用到了联合索引的的age也就是WHERE的条件
EXPLAIN SELECT * FROM student WHERE age = 45 ORDER BY classid, `name`;

# 无法使用索引
EXPLAIN SELECT * FROM student WHERE classid = 45 ORDER BY age;

# 无法使用索引
EXPLAIN SELECT * FROM student WHERE classid = 45 ORDER BY age LIMIT 10;


# 测试filesort和index排序

# 查看所有索引
SHOW INDEX FROM student;
# 删除索引
DROP INDEX idx_age_cid_stuno ON student;

EXPLAIN SELECT * FROM student WHERE age = 30 AND stuno < 101000 ORDER BY `name`;

# 为了消除filesort，我们建立索引
# 创建索引
CREATE INDEX idx_age_name ON student(age, `name`);

EXPLAIN SELECT * FROM student WHERE age = 30 AND stuno < 101000 ORDER BY `name`;

# 创建索引
CREATE INDEX idx_age_stuno_name ON student (age, stuno, `name`);

EXPLAIN SELECT * FROM student WHERE age = 30 AND stuno < 101000 ORDER BY `name`;


# 6. 覆盖索引
# 查询所有索引
SHOW INDEX FROM student;
DROP INDEX idx_age_name ON student;
DROP INDEX idx_age_stuno_name ON student;

# 添加索引
CREATE INDEX idx_age_name ON student(age, `name`);

EXPLAIN SELECT * FROM student WHERE age <> 20;

EXPLAIN SELECT age, `name` FROM student WHERE age <> 20;

EXPLAIN SELECT * FROM student WHERE `name` LIKE '%abc';

EXPLAIN SELECT id, age, `name` FROM student WHERE `name` LIKE '%abc';


# 7. 索引条件下推（ICP）


EXPLAIN SELECT * FROM s1 WHERE key1 > 'z' AND key1 LIKE '%a';


# 准备数据
CREATE TABLE `people` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`zipcode` VARCHAR(20) COLLATE utf8_bin DEFAULT NULL,
	`firstname` VARCHAR(20) COLLATE utf8_bin DEFAULT NULL,
	`lastname` VARCHAR(20) COLLATE utf8_bin DEFAULT NULL,
	`address` VARCHAR(50) COLLATE utf8_bin DEFAULT NULL,
	PRIMARY KEY (`id`),
	KEY `zip_last_first` (zipcode, lastname, firstname)
) ENGINE = INNODB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3 COLLATE =utf8_bin;

INSERT INTO `people` VALUES
('1', '00001', '三', '张', '北京市'),
('2', '00002', '四', '李', '南京市'),
('3', '00003', '五', '王', '上海市'),
('4', '00004', '六', '赵', '天津市');

EXPLAIN SELECT * FROM people
WHERE zipcode = '00001'
AND lastname LIKE '%张%'
AND address LIKE '%北京市%';

# 批量添加数据
DELIMITER $

CREATE PROCEDURE insert_people(max_num INT)
BEGIN
DECLARE i INT DEFAULT 0;
	SET autocommit = 0;
	REPEAT 
	SET i = i + 1;
	INSERT INTO people (zipcode, firstname, lastname, address)
	VALUES
	('000001', '六', '赵', '天津市');
	UNTIL i = max_num
	END REPEAT;
	COMMIT;
END $

DELIMITER ;

CALL insert_people(1000000);

SELECT * FROM people WHERE zipcode='000001' AND lastname LIKE '%张%';