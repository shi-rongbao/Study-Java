# 02-索引的删除与修改

SHOW INDEX FROM book5;

# 方式一：ALTER TABLE ... DROP INDEX ...
ALTER TABLE book5 DROP INDEX uk_idx_info;

# 添加AUTO_INCREMENT约束的字段的唯一索引不能被删除

# 方式二： DROP INDEX ... ON ...
DROP INDEX idx_cmt ON book5;

# 删除表中的一个字段，这个字段包含联合索引 -> 联合索引去掉当前的字段联合
ALTER TABLE book5
DROP COLUMN info;

ALTER TABLE book5
DROP COLUMN book_name;

##  隐藏索引

# ① 创建表时隐藏索引

CREATE TABLE book7(
	book_id INT, 
	book_name VARCHAR(100),
	`authors` VARCHAR(100),
	info VARCHAR(100),
	`comment` VARCHAR(100),
	year_publication YEAR,
	# 创建一个不可见的索引
	INDEX idx_info (info) invisible
);

SHOW INDEX FROM book7;

# 索引被隐藏时不可用
EXPLAIN SELECT * FROM book7 WHERE info = "321";  

# ② 创建表以后，隐藏索引
ALTER TABLE book7
ADD UNIQUE INDEX idx_bname(book_name) invisible;

CREATE INDEX idx_bid ON book7(book_id) invisible;

# 创建一个索引
CREATE INDEX idx_yuar_pub ON book7(year_publication);

# 修改索引的名字
ALTER TABLE book7 RENAME INDEX idx_yuar_pub TO idx_year_pub;

# 将索引修改为不可见的
ALTER TABLE book7 ALTER INDEX idx_year_pub invisible;

# 将索引修改为可见的
ALTER TABLE book7 ALTER INDEX idx_info visible;


/*
注意：
	当索引被隐藏时，他的内容任然是和正常索引一样实时更新的。
	如果一个索引需要长期被隐藏，那么可以将其删除，因为索引的存在会影响插入，更新和删除的性能。
*/