CREATE DATABASE test04_emp;
USE test04_emp;
CREATE TABLE emp2(
id INT,
emp_name VARCHAR(15)
);
CREATE TABLE dept2(
id INT,
dept_name VARCHAR(15)
);

#1.向表emp2的id列中添加PRIMARY KEY约束

ALTER TABLE emp2
ADD  PRIMARY KEY(id);

DESC emp2;


#2. 向表dept2的id列中添加PRIMARY KEY约束

ALTER TABLE dept2
ADD  PRIMARY KEY(id);

DESC dept2;

#3. 向表emp2中添加列dept_id，并在其中定义FOREIGN KEY约束，与之相关联的列是dept2表中的id列。

ALTER TABLE emp2
ADD COLUMN dept_id INT;

ALTER TABLE emp2 
ADD CONSTRAINT fk_emo2_deptid FOREIGN KEY(dept_id) REFERENCES dept2(id);

DESC emp2;

SELECT * FROM information_schema.`TABLE_CONSTRAINTS`
WHERE table_name = 'emp2';

# 1、创建数据库test01_library

DROP DATABASE test01_library;

CREATE DATABASE test01_library;
USE test01_library;
# 2、创建表 books，表结构如下：
CREATE TABLE books(
	id INT COMMENT"书编号",
	`name` VARCHAR(50) COMMENT"书名",
	`authors` VARCHAR(100) COMMENT"作者",
	price FLOAT COMMENT"价格",
	pubdate YEAR COMMENT"出版日期",
	note VARCHAR(100) COMMENT"出版日期",
	num INT COMMENT"库存"
);

SELECT * FROM books;

DESC books;

# 3、使用ALTER语句给books按如下要求增加相应的约束
ALTER TABLE books
MODIFY COLUMN id INT COMMENT"书编号" PRIMARY KEY;

ALTER TABLE books
MODIFY COLUMN  `name` VARCHAR(50) COMMENT"书名"NOT NULL;

ALTER TABLE books
MODIFY COLUMN  `authors` VARCHAR(50) COMMENT"作者"NOT NULL;

ALTER TABLE books
MODIFY COLUMN  price FLOAT COMMENT"价格"NOT NULL;

ALTER TABLE books
MODIFY COLUMN  num INT(11) COMMENT"库存" NOT NULL;

DESC books;



#1. 创建数据库test04_company

CREATE DATABASE test04_company;

USE test04_company;


#2. 按照下表给出的表结构在test04_company数据库中创建两个数据表offices和employees

CREATE TABLE offices(
	office_code INT PRIMARY KEY,
	city VARCHAR(50) NOT NULL,
	address VARCHAR(50),
	country VARCHAR(50) NOT NULL,
	postal_code VARCHAR(15) UNIQUE
);

DESC offices;

CREATE TABLE employees(
	employees_number INT(11) PRIMARY KEY AUTO_INCREMENT,
	last_name VARCHAR(50) NOT NULL,
	first_name VARCHAR(50) NOT NULL,
	mobile VARCHAR(25) UNIQUE,
	office_code INT(10) ,
	job_title VARCHAR(50) NOT NULL,
	birth DATETIME NOT NULL,
	note VARCHAR(255),
	sex VARCHAR(5),
	CONSTRAINT fk_emp_offcode FOREIGN KEY(office_code) REFERENCES offices(office_code)
);

DESC employees;

#3. 将表employees的mobile字段修改到officeCode字段后面

ALTER TABLE employees
MODIFY COLUMN mobile VARCHAR(25) AFTER  office_code;


#4. 将表employees的birth字段改名为employee_birth

ALTER TABLE employees
CHANGE birth employee_birth DATETIME;


#5. 修改sex字段，数据类型为CHAR(1)，非空约束

ALTER TABLE employees
MODIFY COLUMN sex CHAR(1) NOT NULL;

#6. 删除字段note

ALTER TABLE employees
DROP COLUMN note;

#7. 增加字段名favoriate_activity，数据类型为VARCHAR(100)

ALTER TABLE employees
ADD COLUMN favoriate_activity VARCHAR(100);

#8. 将表employees名称修改为employees_info

ALTER TABLE employees
RENAME employees_info;


SELECT * FROM employees_info;

DESC employees_info;

