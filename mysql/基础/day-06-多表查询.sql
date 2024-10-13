# 多表查询
SELECT *
FROM employees
WHERE last_name = 'Abel';

SELECT *
FROM departments
WHERE department_id = 80;

SELECT *
FROM locations
WHERE location_id = 2500;

# 出现笛卡尔积的错误,每一个员工匹配每一个部门,结果导致两种数据相乘得到最终结果数
# 错误的原因:缺少了多表连接的条件
SELECT employee_id, department_name
FROM employees, departments;

# 多表查询的正确方式
SELECT employee_id, department_name
FROM employees, departments
# 两个表的连接条件
# employees表里的department_id要与departments表里的department_id相等
WHERE employees.`department_id` = departments.`department_id`;

# 查询多表共同的字段时,要指定从哪个表中查询
# 但是建议在多表查询时,每个要查询的字段,都指定此字段所在的表
SELECT employees.employee_id, departments.department_name, employees.`department_id`
FROM employees, departments
WHERE employees.`department_id` = departments.`department_id`;

# 可以给表起别名,在SELECT和WHERE中使用,增加SQL语句的可阅读性
# 如果给表起了别名,那就不能使用表原来的名字了
SELECT emp.employee_id, depa.department_name, emp.`department_id`
FROM employees AS emp, departments AS depa
WHERE emp.`department_id` = depa.`department_id`;

# 查询员工的employee_id, last_name, department_name, city
SELECT emp.`employee_id`, emp.`last_name`,depa.`department_name`,loca.`city`
FROM employees AS emp, departments AS depa, locations AS loca
WHERE emp.`department_id` = depa.`department_id`
AND depa.`location_id` = loca.`location_id`;

# 非等值链接:
SELECT *
FROM job_grades;

# 查询last_name 和 grade_level

SELECT e.`last_name`, e.`salary`, j.`grade_level`
FROM employees e, job_grades j
# where e.`salary` between j.`lowest_sal` and j.`highest_sal`;
WHERE e.`salary` >= j.`lowest_sal` AND e.`salary` <= j.`highest_sal`;

# 自连接, 非自连接

SELECT *
FROM employees;

# 自连接
SELECT e.`employee_id`, e.`last_name`, m.`employee_id`,  m.`last_name`
FROM employees AS e, employees AS m 
WHERE e.`manager_id` = m.`employee_id`;


# 左外连接
# 查询员工的last_name, department_name
# SQL92: 左连接时将右表的WHERE条件后加上(+)
# 注意 MySQL不支持SQL92语法的外连接
SELECT e.`last_name`,d.`department_name`
FROM employees e, departments d
WHERE e.`department_id` = d.`department_id`(+);

# SQL99:使用JOIN	ON的方式实现多表查询.这种方式也能解决外连接的问题.MySQL支持这种方式
# SQL99实现内连接:
SELECT e.`last_name`, d.`department_name`
FROM employees e JOIN departments d
ON e.`department_id` = d.`department_id`;

SELECT e.`last_name`, d.`department_name`, l.`city`
FROM employees e
JOIN departments d
ON e.`department_id` = d.`department_id`
JOIN locations l
ON d.`location_id` = l.`location_id`;

# SQL99实现外连接: 左外连接:在JOIN前面加上 LEFT OUTER		OUTER可以省略
#		   又外连接:在JOIN前面加上 RIGHT OUTER		OUTER可以省略
SELECT e.`last_name`, d.`department_name`, l.`city`
FROM employees e
LEFT JOIN departments d
ON d.`department_id` = e.`department_id`
LEFT JOIN locations l
ON d.`location_id` = l.`location_id`;


# 右外连接
SELECT e.`last_name`, d.`department_name`, l.`city`
FROM employees e
RIGHT JOIN departments d
ON d.`department_id` = e.`department_id`
RIGHT JOIN locations l
ON d.`location_id` = l.`location_id`;

# 满外链接
# 将LEFT 或者 RIGHT 改成 FULL 就可以
# 但是, MySQL不支持FULL 满外连接写法
SELECT e.`last_name`, d.`department_name`, l.`city`
FROM employees e
FULL JOIN departments d
ON d.`department_id` = e.`department_id`
FULL JOIN locations l
ON d.`location_id` = l.`location_id`;


# UNION		UNION ALL
# 注意: 能使用 UNION ALL时尽量使用UNION ALL来提高效率
# UNION会执行去重操作,效率较低   UNION ALL 不会执行去重操作,效率较高
# 7 种 JOIN 的实现:
 
# 1. 内连接:
SELECT e.`last_name`, e.`department_id`, d.`department_name`
FROM employees AS e
JOIN departments AS d
ON e.`department_id` = d.`department_id`;

# 2. 左外连接
SELECT e.`employee_id`, e.`last_name`,d.`department_id`, d.`department_name`
FROM employees AS e
LEFT JOIN departments AS d
ON e.`department_id` = d.`department_id`;

# 3.右外连接
SELECT e.`employee_id`, e.`last_name`,d.`department_id`, d.`department_name`
FROM employees AS e
RIGHT JOIN departments AS d
ON e.`department_id` = d.`department_id`;

# 4. 左外部分 将左外链接中的相同部分过滤掉
SELECT e.`employee_id`, e.`last_name`,d.`department_id`, d.`department_name`
FROM employees AS e
LEFT JOIN departments AS d
ON e.`department_id` = d.`department_id`
# WHERE 过滤只留下d.`department_id`中是null的,但是d.`department_id`中没有null,因此全部丢弃
WHERE d.`department_id` IS NULL;

# 5.右外部分 将右外链接中的相同部分过滤掉
SELECT e.`employee_id`, e.`last_name`,d.`department_id`, d.`department_name`
FROM employees AS e
RIGHT JOIN departments AS d
ON e.`department_id` = d.`department_id`
# WHERE 过滤只留下d.`department_id`中是null的,但是d.`department_id`中没有null,因此全部丢弃
WHERE e.`department_id` IS NULL;
# 6.满外连接
# 左外连接UNION ALL右外部分
# 方式1:
SELECT e.`employee_id`, e.`last_name`,d.`department_id`, d.`department_name`
FROM employees AS e
LEFT JOIN departments AS d
ON e.`department_id` = d.`department_id`
UNION ALL
SELECT e.`employee_id`, e.`last_name`,d.`department_id`, d.`department_name`
FROM employees AS e
RIGHT JOIN departments AS d
ON e.`department_id` = d.`department_id`
# WHERE 过滤只留下d.`department_id`中是null的,但是d.`department_id`中没有null,因此全部丢弃
WHERE e.`department_id` IS NULL;
# 方式2:

# 3.右外连接
SELECT e.`employee_id`, e.`last_name`,d.`department_id`, d.`department_name`
FROM employees AS e
RIGHT JOIN departments AS d
ON e.`department_id` = d.`department_id`
UNION ALL
# 4. 左外部分 将左外链接中的相同部分过滤掉
SELECT e.`employee_id`, e.`last_name`,d.`department_id`, d.`department_name`
FROM employees AS e
LEFT JOIN departments AS d
ON e.`department_id` = d.`department_id`
# WHERE 过滤只留下d.`department_id`中是null的,但是d.`department_id`中没有null,因此全部丢弃
WHERE d.`department_id` IS NULL;

# 7.左外部分 UNION ALL 右外部分

SELECT e.`employee_id`, e.`last_name`,d.`department_id`, d.`department_name`
FROM employees AS e
LEFT JOIN departments AS d
ON e.`department_id` = d.`department_id`
# WHERE 过滤只留下d.`department_id`中是null的,但是d.`department_id`中没有null,因此全部丢弃
WHERE d.`department_id` IS NULL
UNION ALL
SELECT e.`employee_id`, e.`last_name`,d.`department_id`, d.`department_name`
FROM employees AS e
RIGHT JOIN departments AS d
ON e.`department_id` = d.`department_id`
# WHERE 过滤只留下d.`department_id`中是null的,但是d.`department_id`中没有null,因此全部丢弃
WHERE e.`department_id` IS NULL;

# NATURAL 自然链接,自动连接两个表中等值的字段

SELECT e.`employee_id`, d.`department_name`
FROM employees AS e NATURAL JOIN departments AS d;

# USING连接
# 将两个表中的相同字段放入USING()中,自动将字段相同的内容筛选出来,必须是同名字段
SELECT e.`employee_id`, d.`department_name`
FROM employees AS e 
JOIN departments AS d
USING(department_id);
