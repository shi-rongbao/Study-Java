SELECT department_id, AVG(salary)
FROM employees AS e
GROUP BY e.department_id;
# 练习:查询部门id为10,20,30,40这四个部门中最高工资比10000高的部门信息

# 方式1
SELECT e.`department_id` , MAX(salary)
FROM employees AS e
WHERE e.`department_id` IN (10,20,30,40)
GROUP BY e.`department_id`
HAVING MAX(salary) > 10000;

# 方式2
SELECT e.`department_id` , MAX(salary)
FROM employees AS e
GROUP BY e.`department_id`
HAVING MAX(salary) > 10000 AND e.`department_id` IN (10,20,30,40);

推荐方式1 
因为 WHERE 的执行效率要高于 HAVING

