# 创建数据库时指明字符集
CREATE DATABASE IF NOT EXISTS dbtest12 CHARACTER SET 'utf8';
SHOW CREATE DATABASE dbtest12;

USE dbtest12;

# 创建表的时候,指明表的字符集
CREATE TABLE temp(
	id INT
) CHARACTER SET 'utf8';

SHOW CREATE TABLE temp;


# 创建表时可以指明字段的字符集
CREATE TABLE IF NOT EXISTS temp1(
	id INT,
	`name` VARCHAR(15) CHARACTER SET 'gbk'
);

SHOW CREATE TABLE temp1;

SHOW VARIABLES LIKE 'character_%';


CREATE TABLE IF NOT EXISTS test_int1(
	f1 INT,
	f2 INT(5),
	f3 INT(5) ZEROFILL
);
# 用ZERIFILL修饰后自动使用 UNSIGNED
DESC test_int1;

INSERT INTO test_int1(f1,f2,f3)
VALUES (123,123,123), (123456,123456,123456);

SELECT * FROM test_int1;

SHOW CREATE TABLE test_int1;






CREATE TABLE test_double1(
	f1 FLOAT,
	f2 FLOAT(5,2),
	f3 DOUBLE,
	f4 DOUBLE(5,2)
);

DESC test_double1;

# 定点数类型

# 默认精度与标度为 (10,0)
CREATE TABLE test_edcimal1(
	f1 DECIMAL,
	f2 DECIMAL(5,2)
);

DESC test_edcimal1;

# 位类型 BIT

# 不写默认为BIT(1),可以存0或1
CREATE TABLE test_bit1(
	f1 BIT,
	f2 BIT(5),
	f3 BIT(64)
);

DESC test_bit1;

INSERT INTO  test_bit1 (f1,f2,f3)
VALUES (1,5,120);

 
SELECT BIN(f1),BIN(f2),BIN(f3)
FROM test_bit1;

# 字段加上0之后可以以十进制的形式显示
SELECT f1 + 0, f2 + 0, f3 + 0
FROM test_bit1;


# ENUM 类型
# 添加时忽略大小写
CREATE TABLE test_enum(
	season ENUM('春','夏','秋','冬','unknown')
);


INSERT INTO test_enum
VALUES('春'),('夏');

# 一行只能添加一个信息
INSERT INTO test_enum
VALUES ('春','夏');

# 不能添加不存在的信息
INSERT INTO test_enum
VALUES ('春1');

# 也可以使用索引进行添加
INSERT INTO test_enum
VALUES ('1'), (3);

# 没有限制非空的情况下可以添加NULL
INSERT INTO test_enum
VALUES (NULL);

SELECT * FROM test_enum;


# SET类型
# 与枚举类型类似,但是可以选多个进行添加
# 添加重复的数据会自动过滤,只添加一次
# 也不能添加没有的数据





