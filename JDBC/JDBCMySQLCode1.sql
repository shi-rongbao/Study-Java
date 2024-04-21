CREATE DATABASE atguigu;

USE atguigu;

CREATE TABLE t_user(
	id INT PRIMARY KEY AUTO_INCREMENT COMMENT '用户主键',
	account VARCHAR(20) NOT NULL UNIQUE COMMENT '账户',
	`password` VARCHAR(64) NOT NULL COMMENT '密码',
	nickname VARCHAR(20) NOT NULL COMMENT '昵称'
);

INSERT INTO t_user(account, `password`, nickname)
VALUES
('root', '123456', '经理'),
('admin', '666666', '管理员');

SELECT * FROM t_user;

CREATE TABLE t_bank(
	id INT PRIMARY KEY AUTO_INCREMENT COMMENT '账户主键',
	account VARCHAR(20) NOT NULL UNIQUE COMMENT'账号',
	money INT UNSIGNED COMMENT'金额,不能为负值'
);

INSERT INTO t_bank(account, money)
VALUES
('srb', 2093812713),
('lvdandan',1000);

SELECT * FROM t_bank;

