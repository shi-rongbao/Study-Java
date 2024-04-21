#1.where子句可否使用组函数进行过滤?
不可以,要使用 HAVING 

#2.查询公司员工工资的最大值，最小值，平均值，总和

SELECT MAX(e.salary) AS "max_salary", MIN(e.salary) AS "min_salary", AVG(e.salary) "avg_salary", SUM(e.salary) AS "sum_salary"
FROM employees AS e;

#3.查询各job_id的员工工资的最大值，最小值，平均值，总和

SELECT e.`job_id`, MAX(e.salary) AS "max_salary", MIN(e.salary) AS "min_salary", AVG(e.salary) "avg_salary", SUM(e.salary) AS "sum_salary"
FROM employees AS e
GROUP BY e.`job_id`;

#4.选择具有各个job_id的员工人数

SELECT e.`job_id`, COUNT(1)
FROM employees AS e
GROUP BY e.`job_id`;

# 5.查询员工最高工资和最低工资的差距（DIFFERENCE）

SELECT MAX(e.`salary`), MIN(e.`salary`), MAX(e.`salary`) - MIN(e.`salary`) DIFFERENCE
FROM employees AS e;


# 6.查询各个管理者手下员工的最低工资，其中最低工资不能低于6000，没有管理者的员工不计算在内

SELECT e.`manager_id`, MIN(e.`salary`)
FROM employees AS e
WHERE e.`manager_id` IS NOT NULL # 过滤留下有管理者的员工
GROUP BY e.`manager_id` # 通过管理者id进行分组,将同一个管理者的员工分到一组
HAVING MIN(e.`salary`) >= 6000; # 过滤留下最低工资大于等于6000的员工

# 7.查询所有部门的名字，location_id，员工数量和平均工资，并按平均工资降序

SELECT d.`department_name`,d.`location_id`, COUNT(e.`employee_id`), AVG(e.`salary`) "avg_salary"
FROM employees AS e
RIGHT JOIN departments AS d
ON e.`department_id` = d.`department_id`
GROUP BY d.`department_name`, d.`location_id`
ORDER BY avg_salary DESC;

SELECT department_name, location_id, COUNT(employee_id), AVG(salary) avg_sal
FROM employees e 
RIGHT JOIN departments d
ON e.`department_id` = d.`department_id`
GROUP BY department_name, location_id
ORDER BY avg_sal DESC;

# 8.查询每个工种、每个部门的部门名、工种名和最低工资

SELECT d.`department_name`, e.`job_id`, MIN(e.`salary`)
FROM employees AS e
RIGHT JOIN departments AS d
ON e.`department_id` = d.`department_id`
GROUP BY d.`department_name`, e.`job_id`;

SELECT department_name,job_id,MIN(salary)
FROM departments d LEFT JOIN employees e
ON e.`department_id` = d.`department_id`
GROUP BY department_name,job_id;
