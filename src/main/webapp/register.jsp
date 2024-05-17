<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" pageEncoding="gb2312"%>
<html>
<head>
    <meta charset="UTF-8">
    <title></title>
    <link rel="stylesheet" type="text/css" href="css/login.css"/>
    <script src="js/jquery-1.9.1.min.js" type="text/javascript" charset="utf-8"></script>
</head>
<body bgcolor="#E3E3E3">
<div class="login">
    <div class="content clearfix">
        <div class="content-left">
            <div class="logo">
                <img src="images/logo.png" alt=""/>
                <p>在线注册</p>
            </div>
        </div>
        <div class="content-right">
            <div class="login-form">
                <h2>用户注册/REGISTER</h2>
                <form action="<c:url value="/userServlet"/>" method="post">
                    <div class="account clearfix">
                        <span>登录名：</span>
                        <input type="hidden" name="type" value="register"/>
                        <td><input type="text" name="user_name"/></td>
                    </div>
                    <div class="password clearfix">
                        <span>密　码：</span>
                        <input type="password" name="password" value="" />
                    </div>
                    <div class="btn">
                        <button type="submit">注册</button>
                    </div>
                    单击<a href="index.jsp">这里</a>返回登录界面！
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>