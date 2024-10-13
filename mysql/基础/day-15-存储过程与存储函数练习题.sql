# 存储过程练习题

#0.准备工作

CREATE DATABASE test15_pro_func;

USE test15_pro_func;

#1. 创建存储过程insert_user(),实现传入用户名和密码，插入到admin表中

CREATE TABLE admin(
id INT PRIMARY KEY AUTO_INCREMENT,
user_name VARCHAR(15) NOT NULL,
pwd VARCHAR(25) NOT NULL
);

SELECT * FROM admin;

DELIMITER $

CREATE PROCEDURE insert_user(IN `name` VARCHAR(15), `password` VARCHAR(25))
BEGIN 
	INSERT INTO admin(user_name, pwd)
	VALUES (`name`, `password`);
END $

DELIMITER ;

CALL insert_user('Tom', '123qwer');

#2. 创建存储过程get_phone(),实现传入女神编号，返回女神姓名和女神电话

DELIMITER $

CREATE PROCEDURE get_phone(IN bea_id INT, OUT `name` VARCHAR(15), OUT phone VARCHAR(15))

BEGIN
	SELECT b.`name`, b.phone INTO `name`, phone
	FROM beauty b
	WHERE b.id = bea_id; 
END $

DELIMITER ;

DROP PROCEDURE get_phone;

CALL get_phone(1, @name, @phone);

SELECT @name, @phone;



CREATE TABLE beauty(
id INT PRIMARY KEY AUTO_INCREMENT,
NAME VARCHAR(15) NOT NULL,
phone VARCHAR(15) UNIQUE,
birth DATE
);



INSERT INTO beauty(NAME,phone,birth)
VALUES
('朱茵','13201233453','1982-02-12'),
('孙燕姿','13501233653','1980-12-09'),
('田馥甄','13651238755','1983-08-21'),
('邓紫棋','17843283452','1991-11-12'),
('刘若英','18635575464','1989-05-18'),
('杨超越','13761238755','1994-05-11');

SELECT * FROM beauty;

#3. 创建存储过程date_diff()，实现传入两个女神生日，返回日期间隔大小

DELIMITER $

CREATE PROCEDURE date_diff(IN day1 DATE, IN day2 DATE, OUT days INT)

BEGIN 
	SELECT DATEDIFF(day1, day2) INTO days;
END $


DELIMITER ;

SET @birth1 = '2004-09-22';
SET @birth2 = '2003-09-22';

CALL date_diff(@birth1, @birth2, @days);

SELECT @days;

#4. 创建存储过程format_date(),实现传入一个日期，格式化成xx年xx月xx日并返回

DELIMITER //

CREATE PROCEDURE format_date(IN input_date DATE, OUT `date` VARCHAR(25))
BEGIN 
	SELECT DATE_FORMAT(input_date,'%Y年%m月%d日') INTO `date`;
END //

DELIMITER ;

DROP PROCEDURE format_date;

SET @date = '2003-9-22';

CALL format_date(@date, @str_date);

SELECT @str_date;

#5. 创建存储过程beauty_limit()，根据传入的起始索引和条目数，查询女神表的记录

DELIMITER $

CREATE PROCEDURE beauty_limit(IN start_index INT, IN size INT)

BEGIN
	SELECT * 
	FROM beauty
	LIMIT start_index, size;
END $

DELIMITER ;

DROP PROCEDURE beauty_limit;

CALL beauty_limit(1, 3);


#创建带inout模式参数的存储过程

#6. 传入a和b两个值，最终a和b都翻倍并返回

DELIMITER $

CREATE PROCEDURE method(INOUT a INT, INOUT b INT)

BEGIN 
	SELECT a * 2, b * 2 INTO a, b;
END $

DELIMITER ;

SET @a = 2;
SET @b = 3;

CALL method(@a, @b);

SELECT @a, @b;

#7. 删除题目5的存储过程

DROP PROCEDURE IF EXISTS beauty_limit;

#8. 查看题目6中存储过程的信息

SHOW CREATE PROCEDURE method;

SHOW PROCEDURE STATUS LIKE 'method';



# 存储函数练习题


#0. 准备工作
USE test15_pro_func;
CREATE TABLE employees
AS
SELECT * FROM atguigudb.`employees`;
CREATE TABLE departments
AS
SELECT * FROM atguigudb.`departments`;


#无参有返回
#1. 创建函数get_count(),返回公司的员工个数
# 创建函数前执行此语句,保证函数的创建成功
SET GLOBAL log_bin_trust_function_creators = 1;

DELIMITER $

CREATE FUNCTION get_count()
RETURNS INT

BEGIN 
	RETURN(SELECT COUNT(*) FROM employees);
END $

DELIMITER ;

SELECT get_count();


#有参有返回
#2. 创建函数ename_salary(),根据员工姓名，返回它的工资

DELIMITER $

CREATE FUNCTION ename_salary(`name` VARCHAR(15))
RETURNS DECIMAL(8,2)

BEGIN
	RETURN(SELECT salary FROM employees WHERE last_name = `name`);
END $

DELIMITER ;

SELECT ename_salary('Abel');

DROP FUNCTION ename_salary;

#3. 创建函数dept_sal() ,根据部门名，返回该部门的平均工资

DELIMITER $

CREATE FUNCTION dept_sal(department_name VARCHAR(15))
RETURNS DECIMAL(8, 2)

BEGIN
	RETURN(SELECT AVG(salary) 
			FROM employees e
			JOIN departments d 
			ON d.department_id = e. department_id
			WHERE d.department_name = department_name);
END $

DELIMITER ;

DROP FUNCTION dept_sal;

SELECT dept_sal('Marketing');

#4. 创建函数add_float()，实现传入两个float，返回二者之和

DELIMITER $

CREATE FUNCTION add_float(float1 FLOAT, float2 FLOAT)
RETURNS FLOAT

BEGIN
	SET float1 = float1;
	SET float2 = float2;
	RETURN float1 + float2;
END $

DELIMITER ;

DROP FUNCTION add_float;

SELECT add_float(2.3, 5.1);
