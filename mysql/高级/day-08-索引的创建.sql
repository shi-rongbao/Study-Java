#  01-索引的创建
# 第一种 CREATE TABLE时创建索引

# 隐式的方式创建索引。在声明有主键约束，唯一性约束，外键约束的字段上，会自动的添加相关的索引
CREATE DATABASE dbtest2;

USE dbtest2;

# 添加主键约束会根据主键自动创建索引，聚簇索引
CREATE TABLE dept(
	dept_id INT PRIMARY KEY AUTO_INCREMENT,
	dept_name VARCHAR(20)
);

# 添加唯一性约束和外键约束也会自动创建索引
CREATE TABLE emp(
	emp_id INT PRIMARY KEY AUTO_INCREMENT,
	emp_name VARCHAR(20) UNIQUE,
	dept_id INT,
	CONSTRAINT emp_dept_id_fk FOREIGN KEY(dept_id) REFERENCES dept(dept_id)
);

# 显示的方式来创建索引
# ①创建普通索引
CREATE TABLE book(
	book_id INT,
	book_name VARCHAR(100),
	`authors` VARCHAR(100),
	info VARCHAR(100),
	`comment` VARCHAR(100),
	year_publication YEAR,
	# 声明索引
	INDEX idx_bname(book_name)
);

# 查看索引
# 方式1：
SHOW CREATE TABLE book;  # 图形化页面看起来不是很直观，可以放到xshell命令行来看

# 方式2：
SHOW INDEX FROM book;

# 性能分析工具EXPLAIN
EXPLAIN SELECT * FROM book WHERE book_name = 'mysql高级';

# ②创建唯一索引
CREATE TABLE book1(
	book_id INT,
	book_name VARCHAR(100),
	`authors` VARCHAR(100),
	info VARCHAR(100),
	`comment` VARCHAR(100),
	year_publication YEAR,
	# 声明索引
	UNIQUE INDEX uk_idx_cmt(`comment`)
);

# 查看索引
SHOW INDEX FROM book1;

INSERT INTO book1(book_id, book_name, `authors`, info, `comment`)
VALUES
(1, "mysql高级", "宋红康", "这本书是mysql高级", "讲的很好");

# 有着唯一索引的字段不能重复，也具有唯一约束的功能
INSERT INTO book1(book_id, book_name, `authors`, info, `comment`)
VALUES
(2, "mysql高级2", "宋红康", "这本书是mysql高级2", "讲的很好");  # 错误代码： 1062		Duplicate entry '讲的很好' for key 'book1.uk_idx_cmt'

SELECT * FROM book1;

# ③主键索引
# 主键约束通过定义主键约束来添加主键索引

DROP TABLE book2;
# AUTO_INCREMENT 修饰的字段必须是主键约束
CREATE TABLE book2(
	book_id INT PRIMARY KEY AUTO_INCREMENT,  # 隐式的添加主键索引
	book_name VARCHAR(100),
	`authors` VARCHAR(100),
	info VARCHAR(100),
	`comment` VARCHAR(100),
	year_publication YEAR
);

SHOW INDEX FROM book2;

CREATE TABLE book2(
	book_id INT PRIMARY KEY,  # 隐式的添加主键索引
	book_name VARCHAR(100),
	`authors` VARCHAR(100),
	info VARCHAR(100),
	`comment` VARCHAR(100),
	year_publication YEAR
);

# 通过删除主键约束来删除主键索引
ALTER TABLE book2
DROP PRIMARY KEY;

SHOW INDEX FROM book2;

# ④创建单列索引

CREATE TABLE book3(
	book_id INT,
	book_name VARCHAR(100),
	`authors` VARCHAR(100),
	info VARCHAR(100),
	`comment` VARCHAR(100),
	year_publication YEAR,
	# 声明索引
	UNIQUE INDEX uk_idx_book_name(`book_name`)
);

SHOW INDEX FROM book3;

# ⑤创建联合索引

CREATE TABLE book4(
	book_id INT,
	book_name VARCHAR(100),
	`authors` VARCHAR(100),
	info VARCHAR(100),
	`comment` VARCHAR(100),
	year_publication YEAR,
	# 声明索引
	INDEX idx_mlt_bid_bname_info(book_id, book_name, info)
);

SHOW INDEX FROM book4;

# 分析，可以用上上面创建的索引
EXPLAIN SELECT * FROM book4 WHERE book_id = 1001 AND book_name = 'mysql';

# 分析，用不上上面创建的索引
EXPLAIN SELECT * FROM book4 WHERE book_name = 'mysql';

# ⑥创建全文索引
# FULLTEXT全文索引可以用于全文搜索，并且只为CHAR, VARCHAR, TEXT列创建索引。所以总是对整个列进行，不支持局部（前缀）索引

CREATE TABLE test4(
	id INT NOT NULL,
	`name` CHAR(30) NOT NULL,
	age INT NOT NULL,
	info VARCHAR(255),
	FULLTEXT INDEX futxt_idx_info(info(50))  # 以前50个字符简历索引
);

SHOW INDEX FROM test4;


# 第二种：表已经创建好了，然后再添加索引

CREATE TABLE book5(
	book_id INT,
	book_name VARCHAR(100),
	`authors` VARCHAR(100),
	info VARCHAR(100),
	`comment` VARCHAR(100),
	year_publication YEAR
);

SHOW INDEX FROM book5;

# ① ALTER TABLE ... ADD ...
ALTER TABLE book5 ADD INDEX idx_cmt(`comment`);

ALTER TABLE book5 ADD UNIQUE INDEX uk_idx_info(info);

ALTER TABLE book5 ADD INDEX idx_mul_bname_info (book_name, info);

# ② CREATE INDEX ... ON ...

CREATE TABLE book6(
	book_id INT,
	book_name VARCHAR(100),
	`authors` VARCHAR(100),
	info VARCHAR(100),
	`comment` VARCHAR(100),
	year_publication YEAR
);

SHOW INDEX FROM book6;

CREATE INDEX idx_cmt ON book6(`comment`);

CREATE UNIQUE INDEX uk_idx_info ON book6(info);

CREATE INDEX mul_idx_bid_bname_binfo ON book6(book_id, book_name, info);