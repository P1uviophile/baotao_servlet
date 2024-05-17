<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title></title>
  <style>
    *{
      margin: 0;
      padding: 0;
    }
    .bx{
      top: 0;
      height: 89px;
      width: 100%;
      background: #177ec1;
    }
    .bx .container{
      margin: 0 70px;
      overflow: hidden;
    }
    .bx .container .left{
      width: 500px;
      height: 70px;
      padding-top: 28px;
      color: #FFFFFF;
      font-size: 24px;
      float: left;
    }
    .bx .container .right{
      overflow: clip;
      width: 124px;
      padding-top: 28px;
      float: right;
    }
    .bx .container .right span{
      font-size: 16px;
      color: #fff;
    }
    .bx .container .right .l{
      float: left;
    }
    .bx .container .right .r{
      float: right;
    }
  </style>
</head>
<body>
<div class="bx">
  <div class="container">
    <h2 class="left">在线购物系统</h2>
    <div class="right">
      <a href="<c:url value="/logoutServlet"/>" target="_parent" >退出登录</a>
    </div>
  </div>
</div>
</body>
</html>
