# 基本函数
ABS#取绝对值
RAND#随机数
ROUND#四舍五入
SELECT TRUNCATE(123.456,1) FROM DUAL;#截断
SELECT SQRT(4) FROM DUAL;#求平方根

#角度与弧度换算函数
RADIANS() 将角度转换为弧度,其中,参数x为角度值
SELECT RADIANS(30),RADIANS(45),RADIANS(60) FROM DUAL
DEGREES() 将弧度转换为角度,其中,参数x为弧度值
SELECT DEGREES(2*PI()), DEGREES(PI())FROM DUAL

# 三角函数
SELECT SIN(RADIANS(30)) FROM DUAL;
ASIN

# 指数和对数
# 
SELECT POW(2,5), POWER(2,4), EXP(20) FROM DUAL;
SELECT LN(EXP(2)) FROM DUAL;


# 进制之间的转换
BIN 二进制
HEX 十进制
OCT 八进制
CONV 三个参数 第一个参数为具体的数字,第二个参数是第一个参数的进制,第三个参数是要将参数二进制的数转换为参数三的进制

SELECT BIN(10), HEX(10), OCT(10), CONV(10,2,10)
FROM DUAL;

# 字符串函数

ASCII() 返回字符串中第一个字母在ascii码表中的值
LENGTH() 返回字符串的字节长度,utf8中,汉字占用三个字节,
CHAR_LENGTH 返回字符串的长度 
SELECT ASCII('abc'), LENGTH('abcdefg')
FROM DUAL;

CONCAT 将字符串参数连接起来
SELECT CONCAT(last_name, 'worked for',last_name)
FROM employees;

CONCAT_WS 将字符串连接起来,但是通过第一个参数隔开
SELECT CONCAT_WS('-','hello','world')
FROM DUAL;



# 日期和时间函数

SELECT CURDATE(), CURTIME(), NOW(), SYSDATE()
FROM DUAL;

SELECT UNIX_TIMESTAMP(), UNIX_TIMESTAMP('2018-9-22 15:20:30'),
FROM_UNIXTIME(1537600830)
FROM DUAL;

SELECT YEAR(NOW()), MONTH(NOW()), DAY(NOW())
FROM DUAL;

SELECT EXTRACT(SECOND FROM NOW()), EXTRACT(DAY FROM NOW()),
EXTRACT(HOUR_MINUTE FROM NOW())
FROM DUAL;

SELECT TIME_TO_SEC(NOW()), SEC_TO_TIME(63230)
FROM DUAL;






























