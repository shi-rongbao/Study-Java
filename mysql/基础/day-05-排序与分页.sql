USE atguigudb;
SELECT * FROM employees;
# 当没有指定排序规则,查询数据库时,按照添加顺序依次展示

# 按照salary从高到低显示员工信息 
# 使用ORDER BY 对查询到的数据进行排序操作
# 升序: ASC(ascend)
# 降序: DESC(descend)
# 如果在 ORDER BY后没有指定排序规则,则默认升序排列
SELECT employee_id, last_name, salary
FROM employees
ORDER BY salary DESC;

# 可以使用列的别名进行排序
SELECT employee_id, last_name, salary, salary*12 AS annual_sal
FROM employees
ORDER BY annual_sal;

# 列的别名只能在 ORDER BY 中使用,不能在WHERE中使用

# 二级排序
# 显示员工信息,按照department_id的降序排列,如果相同,再按照salary的升序排列
SELECT last_name, department_id, salary
FROM employees
ORDER BY department_id DESC, salary ASC;


# 分页
# 用LIMIT 关键字

SELECT last_name, department_id, salary
FROM employees
LIMIT 0,20;

#第二页的数据
SELECT last_name, department_id, salary
FROM employees
LIMIT 20,20;

#第三页的数据
SELECT last_name, department_id, salary
FROM employees
LIMIT 40,20;

# WHERE		ORDEER BY	LIMIT 的声明顺序如下:
# WHERE 第一

SELECT employee_id, last_name, salary
FROM employees
WHERE salary > 6000
ORDER BY salary DESC
# LIMIT 0,35;
LIMIT 10;

SELECT employee_id, last_name, salary
FROM employees
LIMIT 31,2;

 
# MySQL 8.0新特性, LIMIT	OFFSET
SELECT employee_id, last_name, salary
FROM employees
LIMIT 2 OFFSET 31;

# 查询员工表中工资最高的员工
SELECT employee_id, last_name, salary
FROM employees
ORDER BY salary DESC
LIMIT 0,1;







