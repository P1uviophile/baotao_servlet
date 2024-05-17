<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Title</title>
    <link rel="stylesheet" href="<c:url value="/css/common.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/main.css"/>">
</head>
<style>

    .table-container {
        max-height: 400px; /* 设置最大高度，适当调整以适应你的需求 */
        overflow: auto; /* 添加滚动条 */
    }
    <%--表格设置--%>
    .table11_6 table {
        width:780px;
        margin:15px 0;
        border:0;
    }
    .table11_6 th {
        background-color:#96C7ED;
        color:#000000
    }
    /**
    .table11_6,.table11_6 th,.table11_6 td {
        font-size:0.95em;
        text-align:center;
        padding-left:50px;
        padding-right: 50px;
        border-collapse:collapse;
    }
     */
    .table11_6,.table11_6 th,.table11_6 td {
        font-size:0.95em;
        text-align:center;
        padding: 10px 50px;
        border-collapse:collapse;
        overflow: auto;
    }
    .table11_6 td.id {
        font-size:1em;
        text-align:center;
        padding-left: 20px;
        padding-right: 20px;
        border-collapse:collapse;
    }
    .table11_6 td.name {
        font-size:1em;
        text-align:center;
        padding-left:70px;
        padding-right: 70px;
        border-collapse:collapse;
    }
    .table11_6 td.operate {
        font-size:1em;
        text-align:center;
        padding-left:40px;
        padding-right: 40px;
        border-collapse:collapse;
    }
    .table11_6 th,.table11_6 td {
        border: 1px solid #73b4e7;
        border-width:1px 0 1px 0;
        border:2px inset #ffffff;
    }
    .table11_6 tr {
        border: 1px solid #ffffff;
        overflow: auto;
    }
    .table11_6 tr:nth-child(odd){
        background-color:#dcecf9;
    }
    .table11_6 tr:nth-child(even){
        background-color:#ffffff;
    }

    <%-- 以下是搜索框设置 --%>
    input, button {
        border: none;
        outline: none;
    }
    input {
        width: 80%;
        height: 42px;
    }
    button {
        height: 42px;
        width: 42px;
        position: absolute;
    }
    .bar1 {
        background: #A3D0C3;
    }
    .bar1 input {
        border: 2px solid #7BA7AB;
        border-radius: 5px;
        background: #F9F0DA;
        color: #9E9C9C;
    }
    .bar1 button {
        background: url("/images/soso-white.png");
        border: 2px solid #7BA7AB;
        border-radius: 5px;
        background: #F9F0DA;
        color: #151414;
    }
</style>

<body>
<div class="container">
    <div class="content" style="margin: auto">
        <div class="information-title">
            <span>当前位置>用户管理</span>
            <c:choose>
                <c:when test="${sessionScope.admin}">
                    <div class="bottom fr">
                        <a href="user/userUpdate.jsp">添加用户</a>
                    </div>
                </c:when>
            </c:choose>
        </div>
        <div class="unit-information">
            <div class="unit">
                <p class="unit-content">用户管理</p>
            </div>
        </div>
        <c:choose>
            <c:when test="${sessionScope.admin}">
                <div class="search bar1" align="center">
                    <form action="<c:url value="/userServlet"/>" method="post">
                        <input type="hidden" name="type" value="query">
                        <input type="text" placeholder="输入ID/用户名        (模糊搜索）" name="condition"/>
                        <input type="submit" value="搜索" align="center">
                    </form>
                </div>
                <br>
                <!-- 如果是 admin，输出整个用户表格 -->
                <div class="table-container">
                    <table class=table11_6 align="center">
                        <tr>
                            <th>ID</th><th>用户名</th><th>操作</th>
                        </tr>
                        <c:forEach items="${requestScope.list}" var="user">
                            <tr>
                                <td class="id">${user.user_id}</td>
                                <td class="name">${user.user_name}</td>
                                <td class="operate">
                                    <a href="<c:url value="/userServlet?type=queryById&user_id=${user.user_id}"/>" class="table-view">修改</a>
                                    <a>/</a>
                                    <a href="JavaScript:deleteUser(${user.user_id})" class="table-view">删除</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </c:when>
            <c:otherwise>
                <!-- 如果不是 admin，只输出用户自己的信息 -->
                <!-- 这里可以根据需要显示用户的个人信息 -->
                <div class="table-container">
                    <table class=table11_6 align="center">
                        <tr>
                            <th>ID</th><th>用户名</th><th>操作</th>
                        </tr>
                        <c:forEach items="${requestScope.list}" var="user">
                            <c:if test="${user.user_id == sessionScope.loginUser.user_id}">
                                <tr>
                                    <td class="id">${user.user_id}</td>
                                    <td class="name">${user.user_name}</td>
                                    <td class="operate">
                                        <a href="<c:url value="/userServlet?type=queryById&user_id=${sessionScope.loginUser.user_id}"/>" class="table-view">修改</a>
                                    </td>
                                </tr>
                            </c:if>
                        </c:forEach>
                    </table>
                </div>
                <br><br><br><br>
            </c:otherwise>
        </c:choose>
        <br>
        </div>
    </div>
</div>
</body>
<script>
    function deleteUser(user_id) {
        if (window.confirm("确定要删除id为" + user_id + "的用户吗？")) {
            // 构建包含 user_id 的URL字符串
            var url = "<c:url value='/userServlet'/>?type=delete&user_id=" + user_id;
            // 使用构建好的URL进行重定向
            location.href = url;
        }
    }
</script>
</html>