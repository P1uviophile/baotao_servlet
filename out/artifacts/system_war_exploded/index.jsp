<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title></title>
  <link rel="stylesheet" type="text/css" href="css/login.css"/>
  <script src="js/jquery-1.9.1.min.js" type="text/javascript" charset="utf-8"></script>
</head>
<body>
<div class="login">
  <div class="content clearfix">
    <div class="content-left">
      <div class="logo">
        <img src="images/logo.png" alt=""/>
        <p>在线购物系统</p>
      </div>
    </div>
    <div class="content-right">
      <div class="login-form">
        <h2>用户登录/LOGIN</h2>
        <form action="<c:url value="/loginServlet"/>" method="post">
          <div class="account clearfix">
            <span>账　号：</span>
            <input type="text" name="userName" value="" />
          </div>
          <div class="password clearfix">
            <span>密　码：</span>
            <input type="password" name="password" value="" />
          </div>
          <div class="btn">
            <button type="submit">登录</button>
          </div>
          如果没注册单击<a href="register.jsp">这里</a>注册！
        </form>
      </div>
    </div>
  </div>
</div>

</body>
</html>
