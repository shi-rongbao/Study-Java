#1. 创建数据库test01_office,指明字符集为utf8。并在此数据库下执行下述操作
CREATE DATABASE IF NOT EXISTS test01_office CHARACTER SET 'utf8';

#2. 创建表dept01
/*
字段 类型
id INT(7)
NAME VARCHAR(25)
*/

USE test01_office;

CREATE TABLE dept01(
	id INT(7),
	`name` VARCHAR(25)
);

DESC dept01;

#3. 将表departments中的数据插入新表dept02中

CREATE TABLE IF NOT EXISTS dept02
AS 
SELECT *
FROM atguigudb.departments;

DESC dept02;

SELECT *
FROM dept02;


#4. 创建表emp01
/*
字段 类型
id INT(7)
first_name VARCHAR (25)
last_name VARCHAR(25)
dept_id INT(7)
*/

CREATE TABLE IF NOT EXISTS emp01(
	id INT(7),
	first_name VARCHAR(25),
	last_name VARCHAR(25),
	depe_id INT(7)
);

DESC emp01;

#5. 将列last_name的长度增加到50

ALTER TABLE emp01
MODIFY last_name VARCHAR(50);

#6. 根据表employees创建emp02

CREATE TABLE IF NOT EXISTS emp02
AS 
SELECT *
FROM atguigudb.`employees`;

DESC emp02;

SELECT *
FROM emp02;


#7. 删除表emp01

DROP TABLE IF EXISTS emp01;

#8. 将表emp02重命名为emp01

RENAME TABLE emp02 TO emp01;

SELECT *
FROM emp01;

#9.在表dept02和emp01中添加新列test_column，并检查所作的操作

ALTER TABLE emp01 ADD test_column VARCHAR(10);

DESC emp01;

ALTER TABLE dept02 ADD test_column VARCHAR(10);

DESC dept02;

#10.直接删除表emp01中的列 department_id

ALTER TABLE emp01
DROP COLUMN department_id;


# 1、创建数据库 test02_market

CREATE DATABASE test02_market;

# 2、创建数据表 customers
USE test02_market;

CREATE TABLE IF NOT EXISTS customers(
	c_num INT,
	c_name VARCHAR(50),
	c_contact VARCHAR(50),
	c_city VARCHAR(50),
	c_birth DATE
);

DESC customers;

# 3、将 c_contact 字段移动到 c_birth 字段后面

ALTER TABLE customers 
MODIFY c_contact VARCHAR(50) AFTER c_birth;


# 4、将 c_name 字段数据类型改为 varchar(70)
ALTER TABLE customers
MODIFY c_name VARCHAR(70);

# 5、将c_contact字段改名为c_phone
ALTER TABLE customers
CHANGE c_contact c_phone VARCHAR(50);

# 6、增加c_gender字段到c_name后面，数据类型为char(1)

ALTER TABLE customers
ADD c_gender CHAR(1) AFTER c_name;

# 7、将表名改为customers_info

RENAME TABLE customers TO customers_info;

DESC customers_info;
# 8、删除字段c_city

ALTER TABLE customers_info
DROP COLUMN c_city;



# 1、创建数据库test03_company

CREATE DATABASE test03_company;

# 2、创建表offices

USE test03_company;


CREATE TABLE IF NOT EXISTS offices(
	office_code INT,
	city VARCHAR(30),
	address VARCHAR(50),
	country VARCHAR(50),
	postal_code VARCHAR(25)
);

DESC offices;

# 3、创建表employees

CREATE TABLE IF NOT EXISTS emoloyees(
	emp_num INT,
	last_name VARCHAR(50),
	first_name VARCHAR(50),
	mobile VARCHAR(25),
	`code` INT,
	job_title VARCHAR(50),
	birth DATE,
	note VARCHAR(255),
	sex VARCHAR(5)
);

DESC emoloyees;

# 4、将表employees的mobile字段修改到code字段后面
ALTER TABLE emoloyees 
MODIFY mobile VARCHAR(25) AFTER CODE;

# 5、将表employees的birth字段改名为birthday
ALTER TABLE emoloyees
CHANGE birth birthday DATE;

# 6、修改sex字段，数据类型为char(1)
ALTER TABLE emoloyees
MODIFY sex CHAR(1);


# 7、删除字段note

ALTER TABLE emoloyees
DROP COLUMN note;

# 8、增加字段名favoriate_activity，数据类型为varchar(100)
ALTER TABLE emoloyees
ADD COLUMN favoriate_activity VARCHAR(100);


# 9、将表employees的名称修改为 employees_info

RENAME TABLE emoloyees TO employees_info;

DESC employees_info;
