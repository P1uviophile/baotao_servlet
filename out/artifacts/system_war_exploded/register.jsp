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
                <p>����ע��</p>
            </div>
        </div>
        <div class="content-right">
            <div class="login-form">
                <h2>�û�ע��/REGISTER</h2>
                <form action="<c:url value="/userServlet"/>" method="post">
                    <div class="account clearfix">
                        <span>��¼����</span>
                        <input type="hidden" name="type" value="register"/>
                        <td><input type="text" name="user_name"/></td>
                    </div>
                    <div class="password clearfix">
                        <span>�ܡ��룺</span>
                        <input type="password" name="password" value="" />
                    </div>
                    <div class="btn">
                        <button type="submit">ע��</button>
                    </div>
                    ����<a href="index.jsp">����</a>���ص�¼���棡
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>