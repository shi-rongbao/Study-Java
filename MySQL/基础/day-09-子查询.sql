# 练习: 查询谁的工资壁Abel的高?

SELECT salary
FROM employees
WHERE last_name = 'Abel';

SELECT salary, last_name
FROM employees 
WHERE salary > 11000.00;

方式2 自连接

SELECT e1.`salary`, e2.`salary` 
FROM employees AS e1
JOIN employees AS e2
WHERE e1.last_name = 'Abel'  # 过滤下e1中last_name是abel的人
AND e2.salary > e1.salary;# 过滤下e2中salary 大于 过滤后 e1.salary的员工

# 子查询
SELECT 的嵌套

SELECT salary, last_name
FROM employees 
WHERE salary > (SELECT salary
		FROM employees
		WHERE last_name = 'Abel'
		);

# 单行子查询

# 题目: 查询工资大于149号员工工资的员工信息

SELECT *
FROM employees
WHERE salary >(
		SELECT salary
		FROM employees
		WHERE employee_id = 149
		);

# 题目: 返回job_id与141号员工相同,salary比143号员工多的员工姓名,job_id和工资

SELECT last_name, job_id, salary
FROM employees
WHERE job_id = (
		SELECT job_id
		FROM employees 
		WHERE employee_id = 141
		)
AND salary > (
		SELECT salary
		FROM employees
		WHERE employee_id= 143
		);

# 题目:返回公司工资最少的员工的last_name,job_id和salary

SELECT last_name, job_id, salary
FROM employees
WHERE salary = (
		SELECT MIN(salary)
		FROM employees
		);

# 查询与141号的mannager_id和department_id相同的其他员工的employee_id,manager_id,department_id

SELECT manager_id
FROM employees
WHERE (employee_id = 141 OR employee_id = 174);


SELECT department_id
FROM employees
WHERE (employee_id = 141 OR employee_id = 174);


SELECT employee_id, manager_id, department_id
FROM employees
WHERE manager_id = (
			SELECT manager_id
			FROM employees
			WHERE employee_id =141
			)
AND department_id = (
			SELECT department_id
			FROM employees
			WHERE employee_id =141
			)
AND employee_id != 141;

#题目:查询最低工资大于50号部门最低工资的部门id和其最低工资

#先查询50号部门的最低工资
SELECT MIN(salary)
FROM employees
WHERE department_id =50;

# 再查询整体
SELECT department_id, MIN(salary)
FROM employees
WHERE department_id IS NOT NULL
GROUP BY department_id
HAVING MIN(salary) > (
			SELECT MIN(salary)
			FROM employees
			WHERE department_id =50
			);

# 题目:显示员工的employee_id,city.其中,若员工的department_id与location_id为1800的
# department_id相同,则city为'Canada',其余则为'USA'


SELECT employee_id, last_name, (CASE department_id 
	WHEN (
	SELECT department_id
	FROM departments
	WHERE location_id = 1800
	     ) 
	THEN 'Canada'
	ELSE 'USA'
	END 
				) AS "location"
FROM employees; 

SELECT department_id
FROM departments
WHERE location_id = 1800;

SELECT *
FROM employees
WHERE last_name = ''


SELECT employee_id, last_name, salary
FROM employees
WHERE salary IN
		(SELECT MIN(salary)
		 FROM employees
		 GROUP BY department_id
		);


# 返回其他job_i中比job_id为'IT_PROG'部门任一工资低的员工的员工号,姓名,job_id和salary
SELECT employee_id, last_name, job_id, salary
FROM employees
WHERE job_id != 'IT_PROG'
AND salary < ANY (
		     SELECT salary
		     FROM employees
		     WHERE job_id = 'IT_PROG'
		     );

# 返回其他job_i中比job_id为'IT_PROG'部门所有工资低的员工的员工号,姓名,job_id和salary
 
SELECT employee_id, last_name, job_id, salary
FROM employees
WHERE job_id != 'IT_PROG'
AND salary < ALL (
		     SELECT salary
		     FROM employees
		     WHERE job_id = 'IT_PROG'
		     );
		     
# 查询平均工资最低的部门id
SELECT department_id
FROM employees
GROUP BY department_id
HAVING AVG(salary) = (
SELECT MIN(avg_sal)
FROM (
SELECT AVG(salary) avg_sal
FROM employees
GROUP BY department_id
)AS t_avg_sal
);

方式2:

SELECT department_id
FROM employees
GROUP BY department_id
HAVING AVG(salary) <= ALL (
SELECT AVG(salary) avg_sal
FROM employees
GROUP BY department_id
); 




# 相关子查询
# 题目:查询员工中工资大于本部门平均工资的员工的信息

SELECT last_name, salary, department_id
FROM employees e1
WHERE salary > (
	SELECT AVG(salary)
	FROM employees e2
	WHERE e1.`department_id` = e2.`department_id`
);	     


方式2

SELECT e1.last_name, e1.salary, e1.department_id
FROM employees e1,(
SELECT e2.department_id, AVG(salary) avg_sal
FROM employees e2
GROUP BY department_id) t_avg_sal
WHERE e1.department_id = t_avg_sal.department_id
AND salary > avg_sal;
	     
		     
# 查询员工的id,salary,按照department_name排序

SELECT employee_id, salary
FROM employees e
ORDER BY(
	SELECT department_name
	FROM departments d
	WHERE e.`department_id` = d.`department_id`
) DESC;
		     
# 若employees表中employee_id与job_history表中employee_id相同的数据不小于2,输出这些相同id的员工的id,last_name和job_id

SELECT employee_id, last_name, job_id
FROM employees e
WHERE 2 <= (
		SELECT COUNT(1)
		FROM job_history j
		WHERE e.`employee_id` = j.`employee_id`
);

# 题目:查询公司管理者的employee_id, last_name, job_id, department_id信息
SELECT DISTINCT e2.`employee_id`, e2.`last_name`, e2.`job_id`, e2.`department_id`
FROM employees e1
JOIN employees e2
ON e1.`manager_id` = e2.`employee_id`

SELECT employee_id, last_name, job_id, department_id
FROM employees
WHERE employee_id IN (
	SELECT DISTINCT manager_id
	FROM employees
	WHERE manager_id IS NOT NULL
)

SELECT employee_id, last_name, job_id, department_id
FROM employees e1 
WHERE EXISTS (
	SELECT *
	FROM employees e2
	WHERE e1.`employee_id` = e2.`manager_id`
);

# 题目: 查询departments表中,不存在于employees表中的部门的depart_id和department_name


SELECT department_id,department_name
FROM departments d
WHERE NOT EXISTS(
	SELECT *
	FROM employees e
	WHERE d.`department_id` = e.`department_id`
);


方式2 右外连接
SELECT d.`department_id`, d.`department_name`
FROM employees e
RIGHT JOIN departments d
ON e.`department_id` = d.`department_id`
WHERE e.`department_id` IS NULL






