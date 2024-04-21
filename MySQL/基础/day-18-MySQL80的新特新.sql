# 窗口函数
CREATE DATABASE dbtest18;
USE dbtest18;

CREATE TABLE sales(
	id INT PRIMARY KEY AUTO_INCREMENT,
	city VARCHAR(15),
	county VARCHAR(15),
	sales_value DECIMAL
);

INSERT INTO sales(city, county, sales_value)
VALUES
('北京','海淀',10.00),
('北京','朝阳',20.00),
('上海','黄浦',30.00),
('上海','长宁',10.00);

SELECT * FROM sales;

CREATE TEMPORARY TABLE a # 创建临时表
SELECT SUM(sales_value) AS sales_value
FROM sales;

SELECT * FROM a;

CREATE TEMPORARY TABLE b # 创建临时表
SELECT city, SUM(sales_value)
AS sales_value
FROM sales
GROUP BY city;

SELECT * FROM b;

SELECT s.city AS 城市, s.county AS 区, s.sales_value AS 区销售额, b.sales_value AS 市销售额,
s.sales_value / b.sales_value AS 市比率, a.sales_value AS 总销售额, s.sales_value / a.sales_value AS 总比率 
FROM sales s
JOIN b
ON s.city = b. city
JOIN a
ORDER BY s.city, s.county;


# 使用窗口函数实现:

CREATE TABLE goods(
	id INT PRIMARY KEY AUTO_INCREMENT,
	category_id INT,
	category VARCHAR(15),
	`name` VARCHAR(30),
	price DECIMAL(10, 2),
	stock INT,
	upper_time DATETIME
);

INSERT INTO goods(category_id, category, `name`, price, stock, upper_time)
VALUES
(1, '女装/女士精品', 'T恤', 39.90, 1000, '2020-11-10 00:00:00'),
(1, '女装/女士精品', '连衣裙', 79.90, 2500, '2020-11-10 00:00:00'),
(1, '女装/女士精品', '卫衣', 89.90, 1500, '2020-11-10 00:00:00'),
(1, '女装/女士精品', '牛仔裤', 89.90, 3500, '2020-11-10 00:00:00'),
(1, '女装/女士精品', '百褶裙', 29.90, 500, '2020-11-10 00:00:00'),
(1, '女装/女士精品', '呢绒外套', 399.90, 1200, '2020-11-10 00:00:00'),
(2, '户外运动', '自行车', 399.90, 1000, '2020-11-10 00:00:00'),
(2, '户外运动', '山地自行车', 1399.90, 2500, '2020-11-10 00:00:00'),
(2, '户外运动', '登山杖', 59.90, 1500, '2020-11-10 00:00:00'),
(2, '户外运动', '骑行装备', 399.90, 3500, '2020-11-10 00:00:00'),
(2, '户外运动', '运动外套', 799.90, 500, '2020-11-10 00:00:00'),
(2, '户外运动', '滑板', 499.90, 1200, '2020-11-10 00:00:00');

SELECT * FROM goods;

SELECT ROW_NUMBER() OVER(PARTITION BY category_id ORDER BY price DESC) AS row_num, id, category_id, category, `name`, price, stock
FROM goods;

SELECT * 
FROM (
	SELECT ROW_NUMBER() OVER(PARTITION BY category_id ORDER BY price DESC) AS row_num, id, category_id, category, `name`, price, stock
	FROM goods 
) t
WHERE row_num <= 3;

SELECT RANK() OVER(PARTITION BY category_id ORDER BY price DESC) AS row_num, id, category_id, category, `name`, price, stock
FROM goods;

SELECT * 
FROM (
	SELECT RANK() OVER(PARTITION BY category_id ORDER BY price DESC) AS row_num, id, category_id, category, `name`, price, stock
	FROM goods 
) t
WHERE row_num <= 3;



SELECT DENSE_RANK() OVER(PARTITION BY category_id ORDER BY price DESC) AS row_num, id, category_id, category, `name`, price, stock
FROM goods;


SELECT * 
FROM (
	SELECT DENSE_RANK() OVER(PARTITION BY category_id ORDER BY price DESC) AS row_num, id, category_id, category, `name`, price, stock
	FROM goods 
) t
WHERE row_num <= 3;

# 分布函数

SELECT RANK() OVER (PARTITION BY category_id ORDER BY price DESC) AS r,
PERCENT_RANK() OVER (PARTITION BY category_id ORDER BY price DESC) AS pr,
id, category_id, category, `name`, price, stock
FROM goods
WHERE category_id = 1 ;

SELECT CUME_DIST() OVER(PARTITION BY category_id ORDER BY price ASC) AS cd, id, category_id, category, `name`, price, stock
FROM goods;
