<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title></title>
    <link rel="stylesheet" href="css/Left.css">
</head>
<body>
<div class="nav">
    <div class="nav-list">
        <div class="nav-tit">
            <a href="<c:url value="/userServlet"/>" target="main">
                <img src="images/personal-msg.png" alt="">
                <span>用户管理</span>
            </a>
        </div>
        <div class="nav-tit" id="personal">
                <img src="images/soso-white.png" alt="">
                <span>let's 购</span>
            </a>
        </div>
        <div class="personal-list" id="personal_child">
            <ul>
                <li><a href="<c:url value="/commodityServlet"/>" target="main">在线购物</a></li>
                <li><a href="<c:url value="/ordersServlet"/>" target="main">订单信息</a></li>
            </ul>
        </div>
    </div>
</div>

<script src="js/jquery-1.9.1.min.js" type="text/javascript" charset="utf-8"></script>
<script>
    $(document).ready(function(){
        $('#personal').on('click',function(){
            $('#personal_child').fadeToggle(300);
        });
        let aLi = $('#personal_child li');
        aLi.on('click',function(){
            $(this).addClass('active').siblings('li').removeClass('active');
        })
    });
</script>
</body>
</html>
