CREATE DATABASE mytest1;

CREATE DATABASE mytest2 CHARACTER SET 'GBK';

CREATE DATABASE IF NOT EXISTS mytest2 CHARACTER SET 'utf8';

CREATE DATABASE IF NOT EXISTS mytest3 CHARACTER SET 'utf8';


SHOW CREATE DATABASE mytest3;


# 查看当前连接中有哪些数据库
SHOW DATABASES;

# 切换数据库
USE mytest2;

# 查看当前数据库中保存的数据表有哪些
SHOW TABLES;

# 查看当前使用的数据库
SELECT DATABASE() FROM DUAL;

# 查看指定数据库下保存的数据表
SHOW TABLES FROM mysql;


SHOW CREATE DATABASE mytest2;
# 更改数据库字符集

ALTER DATABASE mytest2 CHARACTER SET 'utf8mb4';

# 尽量不要修改数据表的表名
# 可视化工具中的修改表名,是创建一个新的数据表,将原有的数据拷贝到新的数据表中,然后删除原来的数据表

# 删除数据库
DROP DATABASE mytest1;
DROP DATABASE IF EXISTS mytest2;
SHOW DATABASES;

USE atguigudb;

SHOW CREATE DATABASE atguigudb;

SHOW TABLES;

CREATE TABLE IF NOT EXISTS myemp1(
id INT,
emp_name VARCHAR(15),
hire_date DATE
);

# 查看表结构
DESC myemp1; 

# 如果创建表时没有指定字符集,那么默认使用该表所在数据库的字符集
SHOW CREATE TABLE myemp1;

SELECT * FROM myemp1;

# 基于现有的表创建新的表,并导入数据
CREATE TABLE myemp2
AS
SELECT employee_id, last_name, salary
FROM employees;

DESC myemp2; 
SELECT * FROM myemp2;


# 练习1:创建一个表employee_copy,实现对employees表的复制,包括表数据
DROP TABLE employee_copy

CREATE TABLE IF NOT EXISTS employee_copy
AS 
SELECT *
FROM employees;

DESC employee_copy; 
SELECT * FROM employee_copy;

# 练习2:创建一个表employee_blank,实现对employees表的复制,不包括表数据
DROP TABLE employee_blank

CREATE TABLE IF NOT EXISTS employee_blank
AS 
SELECT *
FROM employees
WHERE NULL;

DESC employee_blank; 
SELECT * FROM employee_blank;


# 修改表

# 添加字段
# 默认添加到最后一个字段
DESC myemp1;

ALTER TABLE myemp1
ADD salary DOUBLE(10,2);

ALTER TABLE myemp1
ADD phone_number VARCHAR(20) FIRST;

ALTER TABLE myemp1
ADD email VARCHAR(50) AFTER emp_name;

# 修改一个字段 数据类型,长度,默认值
# 一般不会修改数据类型

# 修改长度
ALTER TABLE myemp1
MODIFY emp_name VARCHAR(25);

# 修改默认值
ALTER TABLE myemp1
MODIFY emp_name VARCHAR(25) DEFAULT 'name';

# 重命名一个字段
ALTER TABLE myemp1
CHANGE salary monthly_salary DOUBLE(8,2);

# 重命名字段名时可以同时修改长度
ALTER TABLE myemp1
CHANGE email my_email VARCHAR(55);

# 删除一个字段
ALTER TABLE myemp1
DROP COLUMN my_email;

# 重命名表
RENAME TABLE myemp1
TO myemp11;

RENAME TABLE myemp11
TO myemp1;

# 重命名表第二种方式
ALTER TABLE myemp1
RENAME TO myemp11;

# 删除表
DROP TABLE IF EXISTS myemp1;
DROP TABLE IF EXISTS employee_copy;

# 清空表
# 清空表中的数据,但是表的结构还存在
SELECT * FROM employee_copy;
TRUNCATE TABLE employee_copy;
DESC employee_copy;


# COMMIT:提交数据.一旦执行COMMIT,则数据就被永久的保存在了数据库中,意味着数据不可以回滚

# ROLLBACK: 回滚数据,一旦执行ROLLBACK,则可以实现数据的回滚,回滚到最近的一次COMMIT之后.


SELECT *
FROM myemp2;

COMMIT;

SET autocommit = FALSE;

DELETE FROM myemp2;

ROLLBACK;

TRUNCATE TABLE myemp2;
























































































