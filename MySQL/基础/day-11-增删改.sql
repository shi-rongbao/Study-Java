# 添加数据
USE atguigudb;
# 方式一: 一条一条添加数据

CREATE TABLE IF NOT EXISTS emp1(
	id INT,
	`name` VARCHAR(15),
	hire_date DATE,
	salary DOUBLE(10,2)
);

DESC emp1; 

SELECT *
FROM emp1;

INSERT INTO emp1
VALUES(
	1,
	'tom',
	'2003-09-22',
	169853.96
);
# 一定按照声明的字段的先后顺序添加

INSERT INTO emp1(id, hire_date, salary, `name`)
VALUES (2, '2003-9-22', 178963.32, 'shirongbao');


INSERT INTO emp1(id, salary, `name`)
VALUES 
(3, 178963.32, 'shirongbao'),
(4, 9999.22, 'yangpeng');


# 方式二:将查询结果插入到表中
INSERT INTO emp1(id,salary,`name`)
SELECT employee_id, salary, last_name
FROM employees
WHERE department_id IN(60, 70);


DESC emp1;
DESC employees;


# 更新数据(修改数据)

# UPDATE ... SET ...WHERE...

UPDATE emp1
SET hire_date = CURDATE()
WHERE id = 4;

UPDATE emp1
SET hire_date = CURDATE(),
salary = 278963.32
WHERE id = 3;

SELECT *
FROM emp1;

DELETE 
FROM emp1
WHERE id > 100;

USE atguigudb;

CREATE TABLE test1(
	a INT,
	b INT,
	c INT GENERATED ALWAYS  AS  (a + b)  VIRTUAL
);

INSERT INTO test1 (a,b)
VALUES (10,20);

UPDATE test1
SET a = 100;

SELECT * FROM test1;



# 创建数据库test01_library
CREATE DATABASE IF NOT EXISTS test01_library;

# 创建表books,表结构如下

USE test01_library;

CREATE TABLE IF NOT EXISTS books(
	id INT,
	`name` VARCHAR(50),
	`authors` VARCHAR(50),
	price FLOAT,
	pubdate YEAR,
	note VARCHAR(100),
	num INT
);

DESC books;

# 向books表中插入记录
# 1) 不指定字段名称,插入第一条记录
INSERT INTO books
VALUES (
	1,
	'Java核心技术卷I',
	'不知道是谁',
	103.83,
	'2023',
	NULL,
	998
);


# 2) 指定所有字段名称,插入第二条记录
INSERT INTO books(id, `name`, `authors`, price,  pubdate, note, num)
VALUES (
	2,
	'Java核心技术卷II',
	'不知道是谁',
	112.32,
	'2023',
	NULL,
	1024
);


# 3) 同时插入多条记录(剩下的所有记录)
INSERT INTO books(id, `name`, `authors`, price,  pubdate, note, num)
VALUES 
(3, 'Tal of AAA', 'Dickes', 23.00, '1995', 'novel', 11),
(4, 'Emmat', 'Jane Lura', 35, '1993', 'joke', 22),
(5, 'Story of Jane', 'Jane Tim', 40, '2001', 'novel', 0);

INSERT INTO books(id, `name`, `authors`, price,  pubdate, note, num)
VALUES (6, 'Rose Hood', 'Richard haggardd', 28, 2008, 'cartoon', 28);
INSERT INTO books(id, `name`, `authors`, price,  pubdate, note, num)
VALUES (5, 'Story of Jane', 'Jane Tim', 40, '2001', 'novel', 0);

SELECT * FROM books;


# 将小说类型(novel)的书的价格都增加5

UPDATE books
SET price = price + 5
WHERE note = 'novel';


# 将名称为 EmmaT的书的价格改为40,并将说明改为drama

UPDATE books
SET price = 40, note = 'drama'
WHERE `name` = 'Emmat';

# 删除库存为0的记录

DELETE 
FROM books
WHERE num = 0;

# 统计书名中包含字母a的书

SELECT `name`
FROM books
WHERE `name` LIKE "%a%";


# 统计书名中包含a字母的书的数量和库存总量
SELECT SUM(num), COUNT(*)
FROM books 
WHERE `name` LIKE '%a%'

# 找出novel类型的书,按照价格降序排列

SELECT *
FROM books
WHERE note = 'novel'
ORDER BY price DESC;

# 查询图书信息,按照库存量降序排列,如果库存量相同的按照note升序排列

SELECT *
FROM books
ORDER BY num DESC, note ASC;

# 按照note分类统计书的数量

SELECT note, SUM(num)
FROM books
GROUP BY note;

# 按照note分类统计书的库存量,显示库存量超过30本的

SELECT SUM(num) total
FROM books
GROUP BY note
HAVING total > 30

# 查询所有图书,每页显示五本,显示第二页
SELECT *
FROM books
LIMIT 5,5

# 按照note分类统计书的库存量,显示库存最多的

SELECT MAX(num) max_num
FROM books
GROUP BY note
ORDER BY max_num DESC
LIMIT 0,1

# 查询书名达到10个字符的书,不包括里面的空格
SELECT *
FROM books
WHERE CHAR_LENGTH(REPLACE(`name`,' ','')) >= 10;

# 查询书名和类型,其中note值为novel显示小说,law显示法律,medicine显示医药
# cartoon显示卡通,joke显示笑话

SELECT `name` AS "书名", note, CASE note 
WHEN 'novel'  THEN '小说'
WHEN 'low' THEN '法律'
WHEN 'joke' THEN '笑话'
ELSE '其他'
END "类型"
FROM books



