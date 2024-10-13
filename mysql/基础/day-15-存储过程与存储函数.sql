# 创建存储过程

# 准备工作:
CREATE DATABASE dbtest15;
USE dbtest15;

CREATE TABLE employees
AS
SELECT * FROM atguigudb.`employees`;

CREATE TABLE departments
AS
SELECT * FROM atguigudb.`departments`;

DELIMITER $

CREATE PROCEDURE select_all_data()
BEGIN
	SELECT * FROM employees;
END$

DELIMITER ;


# 存储过程的调用

# 类型1 : 无参数无返回值
CALL select_all_data;

DELIMITER $

CREATE PROCEDURE avg_employee_salary()
BEGIN
	SELECT AVG(salary) FROM employees;
END$

DELIMITER ;

CALL avg_employee_salary;


DELIMITER $

CREATE PROCEDURE show_max_salary()
BEGIN
	SELECT MAX(salary) FROM employees;
END$

DELIMITER ;

CALL show_max_salary;

# 类型二 带OUT

DELIMITER $

CREATE PROCEDURE show_min_salary(OUT ms DOUBLE)
BEGIN
	SELECT MIN(salary) INTO ms
	FROM employees;
END $

DELIMITER ;

CALL show_min_salary(@ms);

SELECT @ms;

DELIMITER $

# 类型三 带IN
CREATE PROCEDURE show_someone_salary(IN `name` VARCHAR(20))

BEGIN 
	SELECT salary 
	FROM employees
	WHERE last_name = `name`;
END $

DELIMITER ;

CALL show_someone_salary('Abel');
CALL show_someone_salary(@name);

# 变量赋值
SET @name = 'Abel';
SET @name := 'Abel';

# 类型四带IN 和 OUT


DELIMITER $

CREATE PROCEDURE show_someone_salary2(IN empname VARCHAR(20), OUT empsalary DOUBLE)
BEGIN
	SELECT salary INTO empsalary
	FROM employees
	WHERE last_name = empname;
END $

DELIMITER ;

SET @name1 = 'Abel';

CALL show_someone_salary2(@name1, @empsalary);
SELECT @empsalary;

# 类型无 带INOUT

DELIMITER $

CREATE PROCEDURE show_mgr_name(INOUT empname VARCHAR(25))

BEGIN
	SELECT last_name INTO empname
	FROM employees
	WHERE employee_id  = (
						SELECT manager_id
						FROM employees
						WHERE last_name = empname
						);
END $

DELIMITER ;

SET @empname = 'Abel';

CALL show_mgr_name(@empname);

SELECT  @empname;

SELECT last_name
	FROM employees
	WHERE employee_id  = (
						SELECT manager_id
						FROM employees
						WHERE last_name =  'Abel'
						);


# 存储函数的使用

DELIMITER $

CREATE FUNCTION email_by_name()
RETURNS VARCHAR(25)
	DETERMINISTIC
	CONTAINS SQL
	READS SQL DATA
BEGIN
	RETURN (SELECT email FROM employees WHERE last_name = 'Abel');
END $


DELIMITER ;

# 调用
SELECT email_by_name();

# 创建函数前执行此语句,保证函数的创建成功
SET GLOBAL log_bin_trust_function_creators = 1;

DELIMITER $

CREATE FUNCTION email_by_id(emp_id INT)
RETURNS VARCHAR(20)

BEGIN
	RETURN (SELECT email FROM employees WHERE employee_id = emp_id);
END $

DELIMITER ;


# 调用函数

SELECT email_by_id(101);

DELIMITER $

CREATE FUNCTION count_bu_id(dept_id INT)
RETURNS INT
BEGIN
	RETURN(
		SELECT COUNT(*) FROM employees WHERE department_id = dept_id
	);
END $


DELIMITER ;

SELECT count_bu_id(30);


# 存储过程和存储函数的查看,修改,删除

# 1:
SHOW CREATE PROCEDURE show_someone_salary;

SHOW CREATE FUNCTION count_bu_id;

# 2:
SHOW PROCEDURE STATUS LIKE 'show_max_salary';

SHOW FUNCTION STATUS LIKE 'count_bu_id';

# 3:
SELECT * FROM information_schema.`ROUTINES`
WHERE routine_name = 'email_by_id'

# 修改

ALTER PROCEDURE show_max_salary
SQL SECURITY INVOKER
COMMENT '查询最高工资';


# 删除

DROP PROCEDURE IF EXISTS avg_employee_salary;










