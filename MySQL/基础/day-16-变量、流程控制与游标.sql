# 查看所有或部分系统变量

SHOW GLOBAL VARIABLES;  # 查看全局系统变量

SHOW SESSION VARIABLES; # 查看会话系统变量

SHOW VARIABLES; # 查看会话系统变量

SHOW VARIABLES LIKE 'admin_%';

SHOW VARIABLES LIKE 'character_%';

# 查看指定的系统变量,会先在会话系统变量中查找,如果找不到,再去全局系统变量查找

# 查询会话中的变量时用global会报错,反之也一样

SELECT @@global.max_connections;

SELECT @@global.character_set_client;

SELECT @@session.character_set_client;

SELECT @@session.pseudo_thread_id;

# 优先查询会话系统变量
SELECT @@character_set_client;

SELECT @@session.collation_server;


# 修改系统变量的值

# 1.修改MySQL的配置文件, 修改完成后必须重启MySQL服务

# 2. 修改系统变量,只针对于当前数据库实例有效,重启MySQL服务后,修改就失效了

# GLOBAL
SET @@global.max_connections = 151;

SET GLOBAL max_connections = 161;

# SESSION

# 只针对当前会话有效,新建一次链接后失效
SET @@session.character_set_client = 'GBK';

SELECT @@character_set_client;

SET SESSION character_set_client = 'utf8';


# 用户变量

# 会话用户变量

# 准备工作
CREATE DATABASE dbtest16;
USE dbtest16;

CREATE TABLE employees
AS 
SELECT *
FROM atguigudb.`employees`;

CREATE TABLE IF NOT EXISTS departments 
AS
SELECT *
FROM atguigudb.`departments`;

SELECT * FROM employees;
SELECT * FROM departments;

# 变量的定义

SET @m1 = 1;
SET @m2 := 2;
SET @sum = @m1 + @m2;

SELECT @m1, @m2, @sum;

SELECT @count := COUNT(*) FROM employees;

SELECT @count;

SELECT AVG(salary) INTO @avg_sal FROM employees;

SELECT @avg_sal;

# 局部变量
# 使用DECLARE语句定义一个局部变量
# DECLARE的声明方式必须位置在BEGIN 与 END之间的第一行声明

# 声明格式: DECLARE 变量名 类型 [DEFAULT 值]  如果没有DEFAULT 字句,初始值为NULL


DELIMITER $

CREATE PROCEDURE test_variable ()

BEGIN 

	DECLARE fuck VARCHAR(15);
	DECLARE fuck_you INT;
	DECLARE emp_name VARCHAR(25) ;
	
	SET fuck = 'fuck_you';
	
	SET fuck_you := 10;
	
	SELECT last_name INTO emp_name FROM employees WHERE employee_id = 101;
	
	SELECT fuck, fuck_you, emp_name;
	
END $

DELIMITER ;

CALL test_variable();

DROP PROCEDURE test_pro;

DELIMITER $

CREATE PROCEDURE test_pro()

BEGIN 
	DECLARE emp_name VARCHAR(15);
	DECLARE emp_sal DECIMAL(8, 2);
	SELECT last_name, salary INTO emp_name, emp_sal 
	FROM employees
	WHERE employee_id = 102;
	
	SELECT emp_name, emp_sal;
END $

DELIMITER ;

CALL test_pro();

SELECT last_name, salary 
FROM employees
WHERE employee_id = 102;

DROP PROCEDURE test_pro1;

DELIMITER $

CREATE PROCEDURE test_pro1()

BEGIN 
	DECLARE a INT;
	DECLARE b INT;
	DECLARE c INT;
	
	SET a := 2;
	SET b := 3;
	SET c := a +b;
	
	SELECT c;
END $

DELIMITER ;

CALL test_pro1();



SET @a = 2, @b = 4;
SET @c = @a + @b;

SELECT @c;


DROP PROCEDURE differert_salary;

DELIMITER $

CREATE PROCEDURE differert_salary(IN emp_id INT, OUT dif_salary DECIMAL(8, 2))

BEGIN
	DECLARE emp_sal DECIMAL(8, 2) DEFAULT 0.0;
	DECLARE mgr_sal DECIMAL(8, 2) DEFAULT 0.0;
	DECLARE mgr_id INT;
	DECLARE differert DECIMAL(8, 2);
	
	SELECT salary INTO emp_sal 
	FROM employees
	WHERE employee_id = emp_id;

	SELECT manager_id INTO mgr_id
	FROM employees
	WHERE employee_id = emp_id;
	
	SELECT salary INTO mgr_sal
	FROM employees
	WHERE employee_id = mgr_id;
	
	SET dif_salary = mgr_sal - emp_sal;
END $

DELIMITER ;

SET @emp_id := 104;
SET @dif_salary := 0;
CALL differert_salary(@emp_id, @dif_salary);
SELECT @dif_salary;



# 定义"ERROR 1148(42000) "错误, 名称为command_not_allowed.

 DECLARE command_not_allowed CONDITION FOR 1148;
 DECLARE command_not_allowed CONDITION FOR SQLSTATE '42000';


# 定义存储过程,体现错误的处理程序

DELIMITER $

CREATE PROCEDURE UpdateDataNoCondition()

BEGIN 
	DECLARE CONTINUE HANDLER FOR 1048 SET @prc_value = -1;
	SET @x = 1;
	UPDATE employees SET email  = NULL WHERE last_name = 'Abel';
	SET @x = 2;
	UPDATE employees SET email = 'Abel@gmail.com' WHERE last_name = 'Abel';
	SET @x = 3;
END $

DELIMITER ;

CALL UpdateDataNoCondition();
SELECT @prc_value, @x;

SELECT email FROM employees WHERE last_name = 'Abel';

DESC departments;

ALTER TABLE departments
ADD CONSTRAINT uk_dept_name1 UNIQUE (department_name);

DROP PROCEDURE InsertDataWithCondition;

DELIMITER $

CREATE PROCEDURE InsertDataWithCondition()

BEGIN
	# 方式1 :
	#declare exit handler for 1062 set pro_value = -1;
	# 方式2:
	#declare exit handler for sqlstate '23000' set pro_value = -1;
	# 方式3:
	DECLARE duplicate_entry CONDITION FOR 1062;
	DECLARE EXIT HANDLER FOR duplicate_entry SET @pro_value = -1;
	SET @x = 1;
	INSERT INTO departments (department_name) VALUES('测试');
	SET @x = 2;
	INSERT INTO departments (department_name) VALUES('测试');
	SET @x = 3;
END $

DELIMITER ;

SELECT department_name FROM departments;

SELECT * FROM departments;

DELETE  
FROM departments
WHERE department_id = 0;

CALL InsertDataWithCondition();
SELECT @x, @pro_value;

DROP PROCEDURE test_if;

# 分支结构

DELIMITER $

CREATE PROCEDURE test_if()

BEGIN
	# 情况1:
	/*
	DECLARE stu_name VARCHAR(15);
	IF stu_name IS NULL THEN  SELECT'stu_name is null';
	*/
	# 情况2:
	/*
	DECLARE email VARCHAR(25);
	IF email IS NULL THEN SELECT 'email is null';
	ELSE SELECT 'email is not null';
	*/
	# 情况3:
	DECLARE age INT DEFAULT 20;
	IF age > 40 THEN SELECT '老东西';
	ELSEIF age > 18 THEN SELECT '成年人';
	ELSE SELECT '小东西';
	END IF;
END $

DELIMITER ;

CALL test_if();

DROP PROCEDURE update_salary_by_eid1;

DELIMITER $

CREATE PROCEDURE update_salary_by_eid1(IN emp_id INT)

BEGIN
	# 声明局部变量
	DECLARE emp_sal DOUBLE;
	DECLARE emp_hire_year DOUBLE;
	
	SELECT salary INTO emp_sal FROM employees WHERE employee_id = emp_id;
	SELECT DATEDIFF(CURDATE(), hire_date) / 365 INTO emp_hire_year FROM employees WHERE employee_id = emp_id;
	#判断
	IF emp_sal < 8000 AND emp_hire_year > 5 
	THEN UPDATE employees SET salary = salary + 500 WHERE employee_id = emp_id;
	END IF;
END $

DELIMITER ;

SELECT DATEDIFF(CURDATE(), hire_date) / 365, employee_id ,salary FROM employees
WHERE salary < 8000
AND DATEDIFF(CURDATE(), hire_date) / 365 > 5;

CALL update_salary_by_eid1(104);


DELIMITER $

CREATE PROCEDURE update_salary_by_eid2(IN emp_id INT)

BEGIN 
	DECLARE emp_sal DOUBLE;
	DECLARE hire_year DOUBLE;
	
	SELECT salary INTO emp_sal FROM employees WHERE employee_id = emp_id;
	SELECT DATEDIFF(CURDATE(), hire_date) / 365 INTO hire_year FROM employees WHERE employee_id = emp_id;

	IF emp_sal < 9000 AND hire_year > 5
	THEN UPDATE employees SET salary = salary + 500 WHERE employee_id = emp_id;
	ELSE UPDATE employees SET salary = salary + 100 WHERE employee_id = emp_id;
	END IF;
END $

DELIMITER ;

CALL update_salary_by_eid2(103);
CALL update_salary_by_eid2(104);

SELECT DATEDIFF(CURDATE(), hire_date) / 365, employee_id ,salary FROM employees
WHERE salary < 8000
AND DATEDIFF(CURDATE(), hire_date) / 365 >= 5;

SELECT DATEDIFF(CURDATE(), hire_date) / 365, employee_id ,salary FROM employees
WHERE employee_id IN (103, 104);

DELIMITER $

CREATE PROCEDURE update_salary_by_eid3(IN emp_id INT)

BEGIN
	DECLARE emp_sal DOUBLE;
	DECLARE bouns DOUBLE;
	
	SELECT salary INTO emp_sal FROM employees WHERE employee_id = emp_id;
	SELECT commission_pct INTO bouns FROM employees WHERE employee_id = emp_id;
	
	IF emp_sal < 9000 THEN
	UPDATE employees SET salary = 9000 WHERE employee_id = emp_id;
	ELSEIF emp_sal <10000 AND bouns IS NULL THEN
	UPDATE employees SET commission_pct = 0.01WHERE employee_id = emp_id;
	ELSE UPDATE employees SET salary = salary + 100 WHERE employee_id = emp_id;
	END IF;
END $

DELIMITER ;

CALL update_salary_by_eid3(104);
CALL update_salary_by_eid3(103);
CALL update_salary_by_eid3(102);

SELECT * FROM employees;

SELECT employee_id, salary, commission_pct FROM employees WHERE employee_id IN (102, 103, 104);


# 分支结构 CASE
DELIMITER $

CREATE PROCEDURE test_case()
BEGIN
	/* 第一种情况:
	declare var int default 2;
	case var
	when 1 then select 'var = 1';
	when 2 then select 'var = 2';
	WHEN 3 THEN SELECT 'var = 3';
	else select 'other value';
	end case;
	*/
	# 第二种情况:
	DECLARE var1 INT DEFAULT 10;
	CASE 
	WHEN var1 > 100 THEN SELECT 'var > 100';
	WHEN var1 >= 10 THEN SELECT 'var > 10';
	ELSE SELECT 'var';
	END CASE;
END $

DELIMITER ;

CALL test_case();

DROP PROCEDURE test_case;



DELIMITER $

CREATE PROCEDURE update_salary_eid4(IN emp_id INT)
BEGIN 
	DECLARE emp_sal DOUBLE;
	DECLARE bouns DOUBLE;
	SELECT salary INTO emp_sal FROM employees WHERE employee_id = emp_id;
	SELECT commission_pct INTO bouns FROM employees WHERE employee_id = emp_id;
	CASE 
	WHEN emp_sal < 9000 THEN UPDATE employees SET salary = 9000 WHERE employee_id = emp_id;
	WHEN emp_sal < 10000 AND bouns IS NULL THEN UPDATE employees SET commission_pct = 0.01 WHERE employee_id = emp_id;
	ELSE UPDATE employees SET salary = salary + 100 WHERE employee_id = emp_id;
	END CASE;
END $

DELIMITER ;

CALL update_salary_eid4(103);
CALL update_salary_eid4(104);
CALL update_salary_eid4(105);

DROP PROCEDURE update_salary_eid4;

SELECT * FROM employees
WHERE employee_id IN (103, 104, 105);


DELIMITER $

CREATE PROCEDURE update_salary_by_eid5(IN emp_id INT)
BEGIN
	DECLARE hire_year INT;
	SELECT ROUND(DATEDIFF(CURDATE(), hire_date) / 365) INTO hire_year FROM employees WHERE employee_id = emp_id;
	CASE hire_year 
	WHEN 0 THEN UPDATE employees SET salary = salary + 50 WHERE employee_id = emp_id;
	WHEN 1 THEN UPDATE employees SET salary = salary + 100 WHERE employee_id = emp_id;
	WHEN 2 THEN UPDATE employees SET salary = salary + 200 WHERE employee_id = emp_id;
	WHEN 3 THEN UPDATE employees SET salary = salary + 300 WHERE employee_id = emp_id;
	WHEN 4 THEN UPDATE employees SET salary = salary + 400 WHERE employee_id = emp_id;
	ELSE UPDATE employees SET salary = salary + 500 WHERE employee_id = emp_id;
	END CASE;
END $

DELIMITER ;

CALL update_salary_by_eid5(101);

SELECT ROUND(DATEDIFF(CURDATE(), hire_date) / 365) 
FROM employees;

SELECT * FROM employees WHERE employee_id = 101;

DROP PROCEDURE update_salary_by_eid5;


# 循环结构之LOOP

DELIMITER $

CREATE PROCEDURE test_loop()	
BEGIN
		DECLARE num INT DEFAULT 1;
		loop_lable: LOOP
			SET num = num + 1;
			IF num = 10 THEN LEAVE loop_lable;
			END IF;
		END LOOP;
		SELECT num;
END$

DELIMITER ;

DROP PROCEDURE test_loop;

CALL test_loop();

DROP PROCEDURE update_salary_loop;

DELIMITER $

CREATE PROCEDURE update_salary_loop(OUT num INT)
BEGIN
	DECLARE avg_sal DOUBLE;
	DECLARE loop_count INT DEFAULT 0;

	SELECT AVG(salary) INTO avg_sal FROM employees;
	
	loop_lab : LOOP
		IF avg_sal >= 12000
			THEN LEAVE loop_lab;
		END IF;
		UPDATE employees SET salary = salary * 1.1;
		# 更新avg(salary) 的值
		SELECT AVG(salary) INTO avg_sal FROM employees;
		# 记录循环的次数
		SET loop_count = loop_count +1;
	END LOOP loop_lab;
	SET num = loop_count;
END $

DELIMITER ;

UPDATE employees SET salary = salary - 5000;

SELECT AVG(salary) FROM employees;

CALL update_salary_loop(@num);

SELECT @num;


# 循环结构之WHILE

DELIMITER $

CREATE PROCEDURE test_while()
BEGIN
	DECLARE num INT DEFAULT 1;
	WHILE num <= 10 DO 
	SET num = num + 1;
	END WHILE;
	
	SELECT num;
END $

DELIMITER ;

DROP PROCEDURE test_while;

CALL test_while();



DELIMITER $

CREATE PROCEDURE update_salary_while(OUT num INT)
BEGIN
	DECLARE avg_sal DOUBLE;
	DECLARE while_count INT DEFAULT 0;
	SELECT AVG(salary) INTO avg_sal FROM employees;
	WHILE avg_sal > 5000 DO
	UPDATE employees SET salary = salary * 0.9;
	SET while_count = while_count + 1;
	SELECT AVG(salary) INTO avg_sal FROM employees;
	END WHILE;
	
	SET num = while_count;
END $

DELIMITER ;

DROP PROCEDURE update_salary_while;

SELECT AVG(salary) FROM employees;

CALL update_salary_while(@num);

SELECT @num;


# 循环结构之REPEAT


DELIMITER $

CREATE PROCEDURE test_repeat()
BEGIN
	DECLARE num INT DEFAULT 1;
	REPEAT 
		SET num = num +1;
	UNTIL num = 10 END REPEAT;
	SELECT num;
END $

DELIMITER ;

CALL test_repeat();



DELIMITER $

CREATE PROCEDURE update_salary_repeat(OUT num INT)

BEGIN
	DECLARE avg_sal DOUBLE;
	DECLARE repeat_count INT DEFAULT 0;
	
	SELECT AVG(salary) INTO avg_sal FROM employees;
	REPEAT 
		UPDATE employees SET salary = salary * 1.15;
		SET repeat_count = repeat_count + 1;
		SELECT AVG(salary) INTO avg_sal FROM employees;
		UNTIL avg_sal >= 13000 END REPEAT;
	SET num = repeat_count;
END $

DELIMITER ;

DROP PROCEDURE update_salary_repeat;

SELECT AVG(salary) FROM employees;

CALL update_salary_repeat(@num);

SELECT @num;



# REPEAT 最少都会执行一次,先执行,再判断


DELIMITER $

CREATE PROCEDURE leave_begin(IN num INT)

begin_lable:BEGIN
	IF num <= 0 THEN LEAVE begin_lable;
	ELSEIF num = 1 THEN SELECT AVG(salary) FROM employees;
	ELSEIF num = 2 THEN SELECT MIN(salary) FROM employees;
	ELSE SELECT MAX(salary) FROM employees;
	END IF;
	SELECT COUNT(*) FROM employees;
END $

DELIMITER ;

CALL leave_begin(3);

SELECT MIN(salary) FROM employees;

SELECT salary FROM employees;


DELIMITER $

CREATE PROCEDURE leave_while(OUT num INT)

BEGIN
	DECLARE avg_sal DOUBLE;
	DECLARE while_count INT DEFAULT 0;
	SELECT AVG(salary) INTO avg_sal FROM employees;
	while_label:WHILE TRUE DO
		IF avg_sal <= 10000 THEN 
			LEAVE while_label;
		END IF;
	
		UPDATE employees SET salary = salary * 0.9;
		SET while_count = while_count + 1;
	
		SELECT AVG(salary) INTO avg_sal FROM employees;
	END WHILE;
	SET num = while_count;
END $

DELIMITER ;

DROP PROCEDURE leave_while;


CALL leave_while(@num);
SELECT @num;

SELECT AVG(salary) FROM employees;

SELECT MIN(salary) FROM employees;

UPDATE employees 
SET salary = 3500
WHERE salary < 3500;

DELIMITER $

CREATE PROCEDURE test_iterate()
BEGIN 
	DECLARE num INT DEFAULT 0;
	
	loop_label: LOOP
		SET num = num +1;
		IF num < 10 THEN ITERATE loop_label;
		ELSEIF num > 15 THEN LEAVE loop_label;
		END IF;
		SELECT 'fuck you';
	END LOOP;
END $

DELIMITER ;

CALL test_iterate();



# 游标的使用

DELIMITER $

CREATE PROCEDURE get_count_by_limit_total_salary(IN limit_total_salary DOUBLE, OUT total_count INT)
BEGIN
	DECLARE sum_sal DOUBLE DEFAULT 0.0;
	DECLARE emp_sal DOUBLE;
	DECLARE emp_count INT DEFAULT 0;
	
	DECLARE emp_coursor CURSOR FOR SELECT salary FROM employees ORDER BY salary DESC;
	
	OPEN emp_coursor;
	
	loop_label:LOOP 
	IF sum_sal >= limit_total_salary THEN LEAVE loop_label;
	END IF;
	FETCH emp_coursor INTO emp_sal;
	SET sum_sal = sum_sal + emp_sal;
	SET emp_count = emp_count + 1;
	END LOOP loop_label;
	SET total_count = emp_count;
	CLOSE emp_coursor;
END $

DELIMITER ;

CALL get_count_by_limit_total_salary(200000, @total_count);

SELECT @total_count;

