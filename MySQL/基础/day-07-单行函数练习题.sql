# 1.显示系统时间(注：日期+时间)
SELECT NOW()
FROM DUAL;

# 2.查询员工号，姓名，工资，以及工资提高百分之20%后的结果（new salary）
SELECT e.`employee_id`, e.`last_name`, e.`salary`, e.`salary` * 1.2 AS "new salary"
FROM employees AS e;

# 3.将员工的姓名按首字母排序，并写出姓名的长度（length）
SELECT e.`last_name`, CHAR_LENGTH(e.`last_name`) AS "length"
FROM employees AS e
ORDER BY e.`last_name` ASC;

# 4.查询员工id,last_name,salary，并作为一个列输出，别名为OUT_PUT
SELECT CONCAT(e.`employee_id`, e.`last_name`, e.`salary`) OUT_PUT
FROM employees AS e;

# 5.查询公司各员工工作的年数、工作的天数，并按工作年数的降序排序
SELECT DATEDIFF(NOW(),e.`hire_date`) / 365 AS years, DATEDIFF(NOW(), e.`hire_date`) AS days
FROM employees AS e
ORDER BY years DESC;

# 6.查询员工姓名，hire_date , department_id，满足以下条件：雇用时间在1997年之后，department_id为80 或 90 或110, commission_pct不为空
SELECT e.`last_name`, e.`hire_date`, e.`department_id` 
FROM employees AS e
WHERE (UNIX_TIMESTAMP('1997-01-01') - UNIX_TIMESTAMP(e.`hire_date`)) < 0
AND (e.`department_id` = 80 OR e.`department_id` = 90 OR e.`department_id` = 110)
AND e.`commission_pct` IS NOT NULL;

# 7.查询公司中入职超过10000天的员工姓名、入职时间
SELECT e.`last_name`, e.`hire_date`
FROM employees AS e
WHERE DATEDIFF(NOW(),e.`hire_date`) > 10000;

# 8.做一个查询，产生下面的结果
#<last_name> earns <salary> monthly but wants <salary*3>

SELECT CONCAT(last_name, ' earns ', TRUNCATE(salary, 0) , ' monthly but wants ',
TRUNCATE(salary * 3, 0)) "Dream Salary"
FROM employees;

SELECT CONCAT(e.`last_name`,'earns ',e.`salary`, 'monthly but wants ', (e.`salary` * 3)) AS "Dream Salary"
FROM employees AS e;

#使用CASE-WHEN，按照下面的条件：
-- job grade
-- AD_PRES A
-- ST_MAN B
-- IT_PROG C
-- SA_REP D
-- ST_CLERK E
-- 产生下面的结果
-- Last_name Job_id  	Grade
-- king	     AD_PRES 	A
SELECT last_name AS "Last_name", job_id AS "Job_id",
CASE job_id 
WHEN 'AD_PRES' THEN 'A'
WHEN 'ST_MAN'  THEN 'B'
WHEN 'IT_PROG' THEN 'C'
WHEN 'SA_REP'  THEN 'D'
WHEN 'ST_CLERK'THEN 'E'
ELSE 'F'
END "Grade"
FROM employees

