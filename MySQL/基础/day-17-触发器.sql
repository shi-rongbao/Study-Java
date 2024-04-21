# 准备工作

CREATE DATABASE dbtest17;
USE dbtest17;

CREATE TABLE test_trigger(
	id INT PRIMARY KEY AUTO_INCREMENT,
	t_note VARCHAR(30)
);

CREATE TABLE IF NOT EXISTS test_trigger_log(
	id INT PRIMARY KEY AUTO_INCREMENT,
	t_log VARCHAR(30)
);

# 查看表数据

SELECT * FROM test_trigger;
SELECT * FROM test_trigger_log;

# 创建触发器
DELIMITER $

CREATE TRIGGER before_insert_test_tri
BEFORE INSERT ON test_trigger
FOR EACH ROW
BEGIN
	INSERT INTO test_trigger_log(t_log)
	VALUES ('before insert');
END $

DELIMITER ;

INSERT INTO test_trigger(t_note) VALUES ('test');



DELIMITER $

CREATE TRIGGER after_insert_test_tri
AFTER INSERT ON test_trigger
FOR EACH ROW 
BEGIN
	INSERT INTO test_trigger_log(t_log)
	VALUES ('after insert');
END $

DELIMITER ;

INSERT INTO test_trigger(t_note) VALUES ('test2');


CREATE TABLE employees
AS 
SELECT * FROM atguigudb.`employees`;

CREATE TABLE departments
AS 
SELECT * FROM atguigudb.`departments`;


DELIMITER $

CREATE TRIGGER salary_check_trigger
BEFORE INSERT ON employees
FOR EACH ROW
BEGIN
	DECLARE mgr_sal DOUBLE;
	SELECT salary INTO mgr_sal FROM employees 
	WHERE employee_id = NEW.manager_id;
	IF NEW.salary > mgr_sal
	THEN SIGNAL SQLSTATE 'HY000' SET MESSAGE_TEXT =  '薪资高于领导薪资错误' ;
	END IF;
END $

DELIMITER ;

DESC employees;

INSERT INTO employees(employee_id, last_name, email, hire_date, job_id, salary, manager_id)
VALUES(300, 'Tom', 'tom@gmail.com', CURDATE(), 'AD_VP', 8000,103)

INSERT INTO employees(employee_id, last_name, email, hire_date, job_id, salary, manager_id)
VALUES(301, 'Tom1', 'tom@gmail.com1', CURDATE(), 'AD_VP', 18000,103)

SELECT * FROM employees;


# 查看,删除触发器

SHOW TRIGGERS;

SHOW CREATE TRIGGER salary_check_trigger;

SELECT * FROM information_schema.`TRIGGERS`;

# 删除触发器
DROP TRIGGER IF EXISTS after_insert_test_tri;

