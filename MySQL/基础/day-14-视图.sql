# 视图的本质可以看做一个虚拟的表,本身不存储数据
# 可以看做是存储起来的SELECT 语句

# 如何创建视图
# 准备工作
CREATE DATABASE dbtest14;
USE dbtest14;

CREATE TABLE emps
AS
SELECT * FROM atguigudb.`employees`;

CREATE TABLE dept
AS
SELECT * FROM atguigudb.`departments`;

SELECT * FROM emps;
SELECT * FROM dept;

DESC emps;

# 针对单表

CREATE VIEW vu_emp1
AS SELECT employee_id, last_name, salary
FROM emps;

SELECT * FROM vu_emp1;

CREATE VIEW vu_emp2(emp_id, `name`, monthly_sal)
AS SELECT employee_id, last_name, salary
FROM emps;

SELECT * FROM vu_emp2;

CREATE VIEW vu_emp3(`name`)
AS SELECT last_name
FROM emps;

SELECT * FROM vu_emp3;

CREATE VIEW emp_avg_sal (dept_id, avg_sal)
AS
SELECT department_id, AVG(salary)
FROM emps
WHERE department_id IS NOT NULL
GROUP BY department_id;

SELECT * FROM emp_avg_sal;


# 针对多表

CREATE VIEW vu_emp_dept
AS
SELECT d.department_id, d.department_name, e.employee_id
FROM emps e
JOIN dept d
ON e.department_id = d.department_id;

SELECT * FROM vu_emp_dept;

# 利用视图对数据进行格式化

CREATE VIEW vu_emp_dept1
AS
SELECT CONCAT(e.last_name, '(', d.department_name,')') emp_info
FROM emps e
JOIN dept d
ON e.department_id = d.department_id;


SELECT * FROM vu_emp_dept1;


# 基于视图创建视图

CREATE VIEW vu_emp4
AS
SELECT employee_id, last_name
FROM vu_emp1;

SELECT * FROM vu_emp4;


# 查看视图

SHOW TABLES;

DESCRIBE vu_emp1;

SHOW TABLE STATUS LIKE 'vu_emp1';

SHOW CREATE VIEW vu_emp1;

SELECT * FROM vu_emp1;

SELECT employee_id, last_name, salary
FROM emps;

# 更新视图中的数据
# 一般情况下可以更新视图中的数据
UPDATE vu_emp1
SET salary = 20000
WHERE employee_id = 101;

# 视图中的数据更新失败
# 当更新视图中的字段,基表中不存在时,会导致更新失败

# The target table emp_avg_sal of the DELETE is not updatable
DELETE FROM emp_avg_sal
WHERE department_id = 30;


# 修改视图
DESC vu_emp1;

# 方式1
CREATE OR REPLACE VIEW vu_emp1
AS
SELECT employee_id, last_name, salary, email
FROM emps;

# 方式二
ALTER VIEW vu_emp1
AS
SELECT employee_id, last_name, salary, email, hire_date
FROM emps;

# 删除视图
SHOW TABLES;

DROP VIEW vu_emp4;

DROP VIEW IF EXISTS vu_emp2, vu_emp3;
