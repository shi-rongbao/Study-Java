# 03-索引的设计原则

# 1.数据的准备

CREATE DATABASE atguigudb1;

USE atguigudb1;

CREATE TABLE `student_info`(
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`student_id` INT NOT NULL,
	`name` VARCHAR(20) DEFAULT NULL,
	`course_id` INT NOT NULL,
	`class_id` INT(11) DEFAULT NULL,
	`create_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	PRIMARY KEY(`id`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE `course` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`course_id` INT NOT NULL,
	`course_name` VARCHAR(40) DEFAULT NULL,
	PRIMARY KEY(`id`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

SELECT @@log_bin_trust_function_creators;
SET GLOBAL log_bin_trust_function_creators = 1;

DELIMITER $
CREATE FUNCTION rand_string(n INT)
	RETURNS VARCHAR(255)
BEGIN
	DECLARE chars_str VARCHAR(100) DEFAULT 'abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ';
	DECLARE return_str VARCHAR(255) DEFAULT ' ';
	DECLARE i INT DEFAULT 0;
	WHILE i < n DO
		SET return_str = CONCAT(return_str, SUBSTRING(chars_str, FLOOR(1+RAND() * 52), 1));
		SET i = i + 1;
	END WHILE;
	RETURN return_str;
END $
DELIMITER ;

DROP FUNCTION rand_num;

DELIMITER $
CREATE FUNCTION rand_num(from_num INT, to_num INT) RETURNS INT(11)
BEGIN
DECLARE i INT DEFAULT 0;
SET i = FLOOR(from_num + RAND()* (to_num - from_num + 1));
RETURN i;
END $
DELIMITER ;

DELIMITER $
CREATE PROCEDURE insert_course(max_num INT)
BEGIN
DECLARE i INT DEFAULT 0;
	SET autocommit = 0;
	REPEAT
	SET i = i + 1;
	INSERT INTO course(course_id, course_name) VALUES (rand_num(10000, 10100), rand_string(6));
	UNTIL i = max_num
	END REPEAT;
	COMMIT;
END $
DELIMITER ;

DELIMITER $
CREATE PROCEDURE insert_stu(max_num INT)
BEGIN DECLARE i INT DEFAULT 0;
	SET autocommit = 0;
	REPEAT 
	SET i = i + 1;
	INSERT INTO student_info (course_id, class_id, student_id, `name`) 
	VALUES
	(rand_num(10000, 10100), rand_num(10000, 10200), rand_num(1,200000), rand_string(6));
	UNTIL i = max_num
	END REPEAT;
	COMMIT;
END $
DELIMITER ; 

CALL insert_course(100);
SELECT COUNT(*) FROM course;

CALL insert_stu(1000000);
SELECT COUNT(*) FROM student_info;


# 2.哪些清空适合创建索引

# ①字段的数值有唯一性的限制，不是添加了唯一性约束，因为添加唯一性约束会自动添加唯一性索引

/*
业务上具有唯一特性的字段，即使是组合字段，也必须建成唯一索引。
说明：不要以为唯一索引影响了INSERT的速度，这个速度损耗可以忽略，但提高查找速度是很明显的。
*/

# ②频繁作为WHERE查询条件的字段
# 查看当前student_info表中的索引
SHOW INDEX FROM student_info;  # 这里只有一个主键索引

# 没有索引的情况
SELECT course_id, class_id, `name`, create_time, student_id
FROM student_info
WHERE student_id = 123120;  # 347ms

# 给student_id添加普通索引
ALTER TABLE student_info ADD INDEX idx_sid(student_id);

SHOW INDEX FROM student_info;

# 有索引的情况
SELECT course_id, class_id, `name`, create_time, student_id
FROM student_info
WHERE student_id = 123120;  # 39ms

# ③经常GROUP BY 和 ORDER BY的列
# 按照student_id对学生选修的课程进行分组，显示不同的student_id和课程数量，显示100个
# 有索引的情况
SELECT student_id, COUNT(*) AS num 
FROM student_info
GROUP BY student_id
LIMIT 100;  # 40ms

# 删除student_id索引
ALTER TABLE student_info
DROP INDEX idx_sid;

SHOW INDEX FROM student_info;

# 没有索引的情况
SELECT student_id, COUNT(*) AS num 
FROM student_info
GROUP BY student_id
LIMIT 100;  # 2985ms

# 再测试

# 给GROUP BY 和 ORDER BY 都创建索引
ALTER TABLE student_info
ADD INDEX idx_sid(student_id);

ALTER TABLE student_info
ADD INDEX idx_ctm(create_time);

SHOW  INDEX FROM student_info;

# 有单独的索引
EXPLAIN SELECT student_id, COUNT(*) AS num
FROM student_info
GROUP BY student_id
ORDER BY create_time DESC
LIMIT 100;  #4022ms

# 修改sql_mode

SELECT @@sql_mode;

SET @@sql_mode ='STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

# 添加联合索引
ALTER TABLE student_info
ADD INDEX idx_sid_cre_time(student_id, create_time DESC);

SHOW INDEX FROM student_info;

# 有顺序的联合索引的索引
SELECT student_id, COUNT(*) AS num
FROM student_info
GROUP BY student_id
ORDER BY create_time DESC
LIMIT 100;  #345ms

# 再测试，索引顺序改变
ALTER TABLE student_info
ADD INDEX idx_cre_time_sid(create_time DESC, student_id);

ALTER TABLE student_info
DROP INDEX idx_sid_cre_time;

SHOW INDEX FROM student_info;

# 有逆序的联合索引的索引
EXPLAIN SELECT student_id, COUNT(*) AS num
FROM student_info
GROUP BY student_id
ORDER BY create_time DESC
LIMIT 100;  #3465ms  只使用了idx_sid这个索引

# ④ UPDATE, DELETE 的 WHERE条件列

# 没有添加索引
UPDATE student_info
SET student_id = 10002
WHERE `name` = 'dbawjjhb2312';  # 927ms

# 给当前WHERE条件的name添加索引
ALTER TABLE student_info
ADD INDEX idx_name(`name`);

# WHERE中的字段添加索引后
UPDATE student_info
SET student_id = 10002
WHERE `name` = 'fuckj';  # 2 ms

# ⑤DISTINCT字段需要创建索引

# ⑥多表JOIN链接操作时，创建索引注意事项

/*
首先，链接表的数量尽量不要超过3张，因为每增加一张表就相当于增加了一次嵌套的循环，数量级增长会非常快，严重影响查询的效率。

其次，对WHERE条件创建索引，因为WHERE才是对数据条件的过滤。如果再数据流非常大的情况下，没有WHERE条件过滤是非常可怕的。

最后，对于链接的字段创建索引，并且该字段再多张表的类型必须一致。比如course_id在student_info表和course表中都为int(11)类型，而不能一个是int另一个是varchar类型。
*/

# WHERE条件字段有索引
SELECT student_info.`class_id`, `name`, student_info.student_id, course_name
FROM student_info JOIN course
ON student_info.`course_id` = course.`course_id`
WHERE `name` = 'fuck';  # 1ms

SHOW INDEX FROM student_info;

DROP INDEX idx_name ON student_info;

# WHERE条件字段没有索引
SELECT student_info.`class_id`, `name`, student_info.student_id, course_name
FROM student_info JOIN course
ON student_info.`course_id` = course.`course_id`
WHERE `name` = 'fuck';  # 314ms

# ⑦使用列的类型小的创建索引

# ⑧使用字符串前缀创建索引
# 前缀索引

# ⑨区分度高（散列性高）的列适合作为索引

# ⑩使用最频繁的列放到联合索引的左侧

# ①① 在多个字段都要创建索引的情况下，联合索引优于单值索引

# 限制索引的数目

/*
	在实际工作中，我们也要注意平衡，索引的数目不是越多越好。我们需要限制每张表上的索引数量，建议单表索引数量不超过6个
	原因:
		① 每个索引都需要占用磁盘空间，索引越多，需要的磁盘空间就越大。

		② 索引会影响INSERT, DELETE, UPDATE等语句的性能，因为表中数据更改的同时，索引也会进行调整和更新，会造成负担。

		③ 优化器在选择如何优化查询时，会根据同意信息，对每一个可以用到的索引来进行评估，以生成出一个最好的执行计划，如果同时有很多个索引都可以用户查询，会增加MySQL优化器生成执行计划时间，降低查询性能。
*/

# 3. 哪些情况不适合创建索引

# ① 在WHERE（包括GROUP BY, ORDER BY）中使用不到的字段，不要设置索引

# ② 数据量小的表最好不要使用索引

# ③ 有大量重复数据的列上不要创建索引
# 结论：当数据重复度大，比如高于10％的时候，也不需要对这个字段使用索引。

# ④ 避免对经常更新的表创建过多的索引

# ⑤ 不建议用无序的值作为索引

# ⑥ 删除不再使用或者很少使用的索引

# ⑦ 不要定义冗余或重复的索引


# 小结

/*
	索引是一把双刃剑，可以提高查询效率，但也会降低插入和更新的速度并且占用磁盘空间。
	
	选择索引的最终目的是为了使查询的速度变快，上面给出的原则是最基本的准则，但不能拘泥于上面的准则，要在以后得学习和工作中进行不断地实践，根据应用的实际情况进行分析和判断，选择最合适的索引方式。
*/