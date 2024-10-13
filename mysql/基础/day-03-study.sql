USE atguigudb;
SELECT * FROM employees;
SELECT employee_id AS emp_id, first_name AS 姓名,email AS mail
FROM employees;

# 没有去重的情况查询
SELECT department_id
FROM employees;

# 查询去重后的结果
SELECT DISTINCT department_id
FROM employees;

SELECT DISTINCT department_id,salary
FROM employees;

# null参与运算之后,结果也为null

DESCRIBE employees; # 显示了表中字段的详细信息
# 第二种查询方式 DESC
DESC employees;

# 查询部门为50号的信息
SELECT * 
FROM employees
WHERE department_id = 50;

# 查询last_name 为king的信息
SELECT *
FROM employees
WHERE last_name = 'King';