USE dbtest1;

# 查看GBK字符集的比较规则
SHOW COLLATION LIKE 'gbk%';

# 查看UTF-8字符集的比较规则
SHOW COLLATION LIKE 'utf8%';

# 查看服务器的字符集比较规则
SHOW VARIABLES LIKE '%_server';

# 查看数据库的字符集和比较规则
SHOW VARIABLES LIKE '%_database';

# 查看具体数据库的字符集
SHOW CREATE DATABASE dbtest1;


# 修改具体数据库的字符集
ALTER DATABASE dbtest1 DEFAULT CHARACTER SET 'utf8' COLLATE 'utf8_general_ci';

SHOW CREATE TABLE emp1;


SHOW VARIABLES LIKE '%lower_case_table_names%';

# 在linux下数据库名，表名，表的别名，变量名严格区分大小写
# 关键字与函数名不区分大小写
# 列明，字段名，列的别名在所有情况下也都忽略大小写

USE dbtest1;

USE Dbtest1;  # Unknown database 'Dbtest1'

SELECT * FROM emp1;

SELECT * FROM Emp1;  # Table 'dbtest1.Emp1' doesn't exist

# 可以将linux中的mysql修改为大小写不敏感的
# 在my.cnf配置文件中加入 lower_case_table_names=1，然后重启即可  MySQL5.7

# SQL编写建议
# 1. 关键字和函数名全部大写；
# 2. 数据库名，表名，表别名，字段名，字段别名全部小写;
# 3. SQL语句必须以分号结尾;

SELECT @@session.sql_mode;


CREATE TABLE mytb12(id INT, `name` VARCHAR(16), age INT, dept INT);


INSERT INTO mytb12 (id, `name`, age, dept) 
VALUES
(1, '张三', 20, 1),
(2, '李四', 21, 2),
(3, '王五', 22, 3),
(4, '赵六', 23, 4), 
(5, '孙七', 24, 5),
(6, '周八', 25, 6),
(7, '吴九', 26, 7),
(8, '郑十', 27, 8),
(9, '杨十一', 28, 9),
(10, '刘十二', 29, 10);

SELECT `name`, dept, MAX(age)
FROM mytb12
GROUP BY dept;

# 设置sql_mode
SET SESSION sql_mode = '';  # 将sql_mode清空后，各种限制都消失了，宽松模式

INSERT INTO mytb12(id, `name`, age)
VALUES
(11, 'tom', 'aa');

SELECT * FROM mytb12;
