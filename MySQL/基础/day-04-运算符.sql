SELECT 100, 100 + 0, 100 - 0, 100 + 1, 100 - 1, 100 + 35.5, 100 - 35.5
FROM DUAL;

# 在SQL中 + 没有拼接的作用,此时会将字符串转换为数值进行计算
SELECT 100 + '1'
FROM DUAL;

# 如果字符串中的内容不是数字,那将字符串中的内容看做0处理
SELECT 100 + 'a'
FROM DUAL;

# 注意,如果有null参与运算,那么结果为null

# 注意,使用%运算符,结果的正负与前面的数字保持一致
SELECT 12 % -5
FROM DUAL;

SELECT * FROM employees;

# 练习,查询员工id为偶数的员工信息
USE atguigudb;
SELECT employee_id AS employee_id
FROM employees;
SELECT * 
FROM employees
WHERE employee_id % 2 = 0;

# <=> 安全等于,两边都是null返回1 只有一个null返回0

# ISNULL IS NULL IS NOT NULL

SELECT commission_pct
FROM employees
WHERE commission_pct IS NOT NULL;

# BETWEEN ... AND ,包括边界值
SELECT first_name,salary 
FROM employees
WHERE salary BETWEEN 6000 AND 8000;

# IN(SET)  NOT IN(SET)

SELECT last_name, salary, department_id
FROM employees
WHERE department_id IN(10,20,30);

SELECT last_name, salary, department_id
FROM employees
WHERE salary NOT IN(6000, 7000, 8000);

# LIKE:模糊查询 忽略大小写
# % 代表不确定个数的字符,可以表示0

SELECT last_name, salary, department_id
FROM employees
WHERE last_name LIKE '%a%';


# 以a开头的信息
SELECT last_name, salary, department_id
FROM employees
WHERE last_name LIKE 'a%';

# 查询last_name中包含a且包含e的
SELECT last_name, salary, department_id
FROM employees
WHERE last_name LIKE '%a%' AND last_name LIKE '%e%';

# _ 代表一个不确定的字符

SELECT last_name, salary, department_id
FROM employees
WHERE last_name LIKE '__a%';

# \ 代表转义字符
# ESCAPE'#'可以将单引号内的内容转变为转义字符

# REGEXP  RLIKE 正则表达式
SELECT 'shirongbao' REGEXP '^s', # shirongbao 是不是以 s为开头的
'shirongbao' REGEXP 'o$', # shirongbao 是不是以 o结尾的
'shirongbao' REGEXP 'ng' # shirongbao 里包不包含 ng
FROM DUAL;