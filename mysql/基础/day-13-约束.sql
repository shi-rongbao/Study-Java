# 如何查看表中的约束
SELECT * FROM information_schema.`TABLE_CONSTRAINTS`
WHERE table_name = 'employees';

# 1 NOT NULL

# 在CREATE TABLE 时添加约束

CREATE DATABASE dbtest13;
USE dbtest13;

CREATE TABLE test1(
	id INT NOT NULL,
	last_name VARCHAR(15) NOT NULL,
	email VARCHAR(25),
	salary DECIMAL(10,2)
);

DESC test1;

INSERT INTO test1(id, last_name, email, salary)
VALUES
(1, 'Tom', 'tom@gmail.com', 3400);

SELECT * FROM test1;

# Field 'id' doesn't have a default value
INSERT INTO test1(last_name, email, salary)
VALUES
('Jerry', 'jerry@gmail.com',3500);

# Column 'last_name' cannot be null
INSERT INTO test1(id, last_name, email, salary)
VALUES
(3, NULL, 'Harry@gmail.com',3600);

# Column 'id' cannot be null
UPDATE test1
SET id = NULL
WHERE id = 1;


# ALTER TABLE 时添加或删除约束
DESC test1;

ALTER TABLE test1
MODIFY email VARCHAR(25) NOT NULL;

ALTER TABLE test1
MODIFY email VARCHAR(25);

# 唯一性约束 UNIQUE
# 在创建表的时候添加约束

CREATE TABLE test2(
	id INT UNIQUE, # 声明在字段后面是列级约束
	last_name VARCHAR(15),
	email VARCHAR(25) ,
	salary DECIMAL(10,2),
	CONSTRAINT ul_test2_email UNIQUE(email) # 声明在这里是表级约束
);

DESC test2;

# 添加NULL值时不会被UNIQUE约束

# 复合的唯一性约束 多列约束

CREATE TABLE `user`(
	id INT,
	`name` VARCHAR(15),
	`password` VARCHAR(25),
	# 表级约束
	UNIQUE(`name`,`password`)
);

INSERT INTO `user`(id, `name`, `password`)
VALUES
(1, 'Tom','123');

INSERT INTO `user`(id, `name`, `password`)
VALUES
(2, 'Tom','1234');

SELECT * FROM `user`;

DESC `user`;


# 删除唯一性约束

DESC test2;

SELECT * FROM information_schema.`TABLE_CONSTRAINTS`
WHERE table_name = 'test2';

ALTER TABLE test2
DROP INDEX ul_test2_email;

ALTER TABLE test2
DROP INDEX id;

# 主键约束 PRIMARY KEY

# Multiple primary key defined
# 一个表中最多只有一个主键约束
CREATE TABLE test3(
	id INT PRIMARY KEY,
	`name` VARCHAR(15) PRIMARY KEY,
	email VARCHAR(25),
	salary DECIMAL
);

# 主键约束的特征:非空且唯一,用于标识表中的一条记录.
CREATE TABLE test3(
	id INT PRIMARY KEY,
	`name` VARCHAR(15),
	email VARCHAR(25),
	salary DECIMAL
);


# mysql的主键名总是PRIMARY,自己命名无效
CREATE TABLE test4(
	id INT ,
	`name` VARCHAR(15),
	email VARCHAR(25),
	salary DECIMAL,
	PRIMARY KEY(id)
);

SELECT * FROM information_schema.`TABLE_CONSTRAINTS`
WHERE table_name = 'test4';


CREATE TABLE test5(
	id INT ,
	`name` VARCHAR(15),
	email VARCHAR(25),
	salary DECIMAL
);

ALTER TABLE test5
ADD PRIMARY KEY(id);

DESC test5;

# 删除主键约束(实际开发中,不会删除表中的主键约束)
ALTER TABLE test5
DROP PRIMARY KEY;

# 自增长列:AUTO_INCREMENT
CREATE TABLE test6(
	id INT PRIMARY KEY,
	`name` VARCHAR(15),
	email VARCHAR(25),
	salary DECIMAL
);

CREATE TABLE IF NOT EXISTS test7(
	id INT PRIMARY KEY AUTO_INCREMENT,
	`name` VARCHAR(15),
	email VARCHAR(25),
	salary DECIMAL
);

INSERT INTO test7(`name`, email, salary)
VALUES('Tom', 'Tom@gmail.com', 3500)

INSERT INTO test7(`name`, email, salary)
VALUES('Jerry', 'Jerry@gmail.com', 3600)

SELECT * FROM test7;

DESC test7;

# 当往主键添加AUTO_INCREMENT时,再次给该字段赋值0或NULL无效

# ALTER TABLE 时添加AUTO_INCREMENT

CREATE TABLE test8(
	id INT,
	`name` VARCHAR(15)
);

ALTER TABLE test8
MODIFY id INT PRIMARY KEY AUTO_INCREMENT;

DESC test8;

# ALTER TABLE 时删除AUTO_INCREMENT

ALTER TABLE test8
DROP AUTO_INCREMENT;

ALTER TABLE test8
MODIFY id INT;

# 创建一个表 用来弥补差距
CREATE TABLE test9(
	id INT,
	`name` VARCHAR(15)
);



# 外键约束 FOREIGN KEY	

# 在CREATE TABLE 时添加

# 先创建主表,再创建从表

CREATE TABLE dept1(
	dept_id INT,
	dept_name VARCHAR(15)
);

# 报错因为emp1表中要关联的外键约束主表中dept_id没有主键约束或者唯一性约束
CREATE TABLE emp1(
	emp_id INT PRIMARY KEY AUTO_INCREMENT,
	emp_name VARCHAR(15),
	department_id INT,
	CONSTRAINT fk_emp1_dept_id FOREIGN KEY(department_id) REFERENCES dept1(dept_id)
);

ALTER TABLE dept1
ADD PRIMARY KEY(dept_id);

DESC dept1;

# 再创建从表
CREATE TABLE emp1(
	emp_id INT PRIMARY KEY AUTO_INCREMENT,
	emp_name VARCHAR(15),
	department_id INT,
	CONSTRAINT fk_emp1_dept_id FOREIGN KEY(department_id) REFERENCES dept1(dept_id)
);

DESC emp1;

SELECT * FROM information_schema.`TABLE_CONSTRAINTS`
WHERE table_name = 'emp1';

# 外键的效果

# 当从表添加数据时,如果外键约束对应的主表中的数据不存在,那么添加失败
# 当主表中有数据被从表中外键约束时,不能直接删除主表,必须先删除从表
# 也不能修改主表中被从表外键约束的字段内容
# ALTER TABLE 时添加外键约束

CREATE TABLE dept2(
	dept_id INT PRIMARY KEY AUTO_INCREMENT,
	dept_name VARCHAR(15)
);

CREATE TABLE emp2(
	emp_id INT PRIMARY KEY AUTO_INCREMENT,
	emp_name VARCHAR(15),
	department_id INT
);

SELECT * FROM information_schema.`TABLE_CONSTRAINTS`
WHERE table_name = 'emp2';

ALTER TABLE emp2
ADD CONSTRAINT fk_emp2_dept_id FOREIGN KEY (department_id) REFERENCES dept2(dept_id);


SELECT * FROM information_schema.`TABLE_CONSTRAINTS`
WHERE table_name = 'emp2';


# 对于外检约束,可以设置等级,`ON UPDATE CASCADE ON DELETE RESTRICT`
# 意为 更新时同步修改从表数据,删除时同步将从表的数据修改为NULL

# 一个表中可以关联多个外键约束

# 删除外键约束

SELECT * FROM information_schema.`TABLE_CONSTRAINTS`
WHERE table_name = 'emp1';

ALTER TABLE emp1
DROP FOREIGN KEY fk_emp1_dept_id;

# 再手动删除外键约束对应的普通索引
# 查看索引
SHOW INDEX FROM emp1;

# 通过外键约束的名字来删除索引
ALTER TABLE emp1 
DROP INDEX fk_emp1_dept_id;


# CHECK 约束
# MySQL从8.0开始支持CKECH约束

CREATE TABLE test10(
	id INT,
	`name` VARCHAR(15),
	salary DECIMAL(10,2) CHECK (salary > 5000)
);

DESC test10;

# Check constraint 'test10_chk_1' is violated.
INSERT INTO test10(id, `name`, salary)
VALUES
(1, 'Tom', 2000);

INSERT INTO test10(id, `name`, salary)
VALUES
(1, 'Tom', 20000);

SELECT * FROM test10;


# DEFAULT约束
# 没有给字段赋值时自动赋值为DEFAULT

CREATE TABLE test11(
	id INT,
	`name` VARCHAR(15),
	salary DECIMAL(10,2),
	gender CHAR(2) DEFAULT '男'
);

DESC test11;

DROP TABLE test11;

SELECT * FROM information_schema.`TABLE_CONSTRAINTS`
WHERE table_name = 'test11';

# ALTER TABLE 时添加DEFAULT 约束


CREATE TABLE test12(
	id INT,
	`name` VARCHAR(15),
	salary DECIMAL(10,2)
);

DESC test12;

ALTER TABLE test12
MODIFY salary DECIMAL(10,2) DEFAULT 2000;

# 删除DEFAULT 约束

ALTER TABLE test12
MODIFY salary DECIMAL(10,2);

# 外键约束不能跨存储引擎使用