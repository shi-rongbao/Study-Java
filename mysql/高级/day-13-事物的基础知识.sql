#  13-事物的基础知识

# 1.事物的完整过程
# 步骤1：开启事务
# 步骤2：一系列的DML操作
# 步骤3：结束的状态：提交的状态（COMMIT），中止的状态(ROLLBACK)


# 2.显式事务

# 2.1 如何开启？	使用关键字：START TRANSACTION 或 BEGIN
# start transaction 后面可以跟：read only / read write （默认）/ with consistent snapshot

# 2.2 保存点（SAVEPOINT）

# 在事物中创建保存点，方便后续针对保存点进行回滚。一个事务中可以存在多个保存点。
# SAVEPOINT 保存点名称;

# 删除某个保存点。
# RELEASE SAVEPOINT 保存点名称; 


# 3.隐式事务

# 3.1 关键字：autocommit;

# DEFAULT = ON
SHOW VARIABLES LIKE '%autocommit%';
# 默认每一条DML操作都是独立的事务

 # 3.2 如何关闭自动提交？
 # 方式1：
SET autocommit = OFF;

# 方式2：
# 我们在autocommit = true的情况下，使用start transaction或begin开启事务，那么DML操作就不会自动提交数据

# 4. 案例分析

# 举例1：
# show variables like 'autocommit';
# set autocommit = true;

USE atguigudb2;

# 情况1：
CREATE TABLE user3(`name` VARCHAR(15) PRIMARY KEY);

SELECT * FROM user3;

BEGIN;
INSERT INTO user3 VALUES('张三');  # 此时不会自动提交数据
COMMIT;

# 开启一个新的事物
BEGIN;
INSERT INTO user3 VALUES('李四');
INSERT INTO user3 VALUES('李四');  # 受主键的影响，不能添加成功
ROLLBACK;

# 情况2：
# DDL操作会自动提交数据，不受autocommit变量的影响
TRUNCATE TABLE user3;

SELECT * FROM user3;

BEGIN;
INSERT INTO user3 VALUES('张三'); 
COMMIT;

INSERT INTO user3 VALUES('李四'); 
INSERT INTO user3 VALUES('李四'); 

ROLLBACK;

SELECT * FROM user3;


# 情况3：
TRUNCATE TABLE user3;

SELECT * FROM user3;

SELECT @@completion_type;

SET @@completion_type = 1;

BEGIN;
INSERT INTO user3 VALUES('张三'); 
COMMIT;

INSERT INTO user3 VALUES('李四'); 
INSERT INTO user3 VALUES('李四'); 

ROLLBACK;

SELECT * FROM user3;

# 举例2：
CREATE TABLE test1(id INT) ENGINE = INNODB;

CREATE TABLE test2(id INT) ENGINE = MYISAM;


# 针对innodb表
BEGIN
INSERT INTO test1 VALUES (1);
ROLLBACK;

SELECT * FROM test1;  # 没有数据了


# 针对myisam表
BEGIN;
INSERT INTO test2 VALUES(1);
ROLLBACK;

SELECT * FROM test2; # 数据还在

# 举例3：
CREATE TABLE user4(`name` VARCHAR(15), balance DECIMAL(10,2));

BEGIN;
INSERT INTO user4(`name`, balance) VALUES('张三', 1000);
COMMIT;

SELECT * FROM user4;

UPDATE user4 SET balance =  1000 WHERE `name` = '张三';


BEGIN;
UPDATE user4 SET balance = balance - 100 WHERE `name` = '张三';
UPDATE user4 SET balance = balance - 100 WHERE `name` = '张三';
SAVEPOINT s1; # 设置保存点
UPDATE user4 SET balance = balance + 1 WHERE `name` = '张三';
ROLLBACK TO s1; # 回滚到保存点
ROLLBACK;

SELECT * FROM user4;
