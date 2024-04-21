USE dbtest1;

SHOW TABLES;

CREATE USER 'srb' IDENTIFIED BY '123456';

SHOW TABLES;

USE mysql;

SHOW TABLES;

SELECT `host`, `user` FROM `user`;

# 为'srb'赋予权限
GRANT INSERT, SELECT, UPDATE, DELETE ON dbtest1.* TO 'srb'@'%';
 
# 创建新用户'lisi'
CREATE USER 'lisi'@'%' IDENTIFIED BY '123456';

# 将所有数据库下的所有权限赋给'lisi'
GRANT ALL PRIVILEGES ON *.* TO 'lisi'@'%';

# 查询用户表
USE mysql;
SELECT `host`, `user` FROM `user`;

# 查看权限
SHOW GRANTS; 

# 回收权限
REVOKE INSERT, SELECT, UPDATE, DELETE ON dbtest1.* FROM  'srb'@'%';

# 给root用户必要的权限
GRANT SYSTEM_USER ON *.* TO 'root'@'%';

# 回收权限
REVOKE ALL PRIVILEGES ON *.* FROM 'lisi'@'%';

# 创建角色
CREATE role 'manager'@'%';

# 给角色赋予权限
GRANT SELECT, UPDATE ON dbtest1.* TO 'manager';

