<%--
  Created by IntelliJ IDEA.
  User: ShiRongbao
  Date: 2024/2/5
  Time: 21:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>登录页面</title>
    <style type="text/css">
        body {
            background-color: #f2f2f2;
            font-family: "Microsoft YaHei", sans-serif;
        }

        .login-box {
            width: 300px;
            margin: 100px auto;
            padding: 20px;
            border: 1px solid #ddd;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        .login-title {
            text-align: center;
            font-size: 20px;
            margin-bottom: 20px;
        }

        .login-input {
            width: 100%;
            height: 40px;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 3px;
            margin-bottom: 10px;
            box-shadow: 0 0 2px rgba(0, 0, 0, 0.1);
        }

        .login-button {
            width: 100%;
            height: 40px;
            background-color: #0099ff;
            color: #fff;
            border: 1px solid #0099ff;
            border-radius: 3px;
            cursor: pointer;
            box-shadow: 0 0 2px rgba(0, 0, 0, 0.1);
        }

        .login-error {
            color: red;
            text-align: center;
        }
    </style>
</head>
<body>

<div class="login-box">

    <div class="login-title">登录</div>

    <form action="login" method="post">

        <input type="text" name="username" class="login-input" placeholder="请输入用户名">

        <input type="password" name="password" class="login-input" placeholder="请输入密码">

        <input type="submit" value="登录" class="login-button">

    </form>

    <div class="login-error">
        ${errorMessage}
    </div>

</div>

</body>
</html>


