# 1. 查询员工的姓名和部门号和年薪,按年薪降序,按姓名升序显示
SELECT department_id, salary * 12 annual_sal, last_name
FROM employees
ORDER BY annual_sal DESC, last_name ASC;

# 2.选择工资不在8000到17000的员工的姓名和工资,按工资降序,显示到地21到40位置的数据
SELECT last_name, department_id, salary
FROM employees
WHERE salary NOT BETWEEN 8000 AND 17000
ORDER BY salary DESC 
LIMIT 20,20;

# 3.查询邮箱中包含 e 的员工信息,并先按照邮箱的字节数排序,再按部门号升序
SELECT last_name, department_id, email, salary
FROM employees
WHERE email LIKE '%e%'
ORDER BY LENGTH(email) DESC, department_id ASC;


