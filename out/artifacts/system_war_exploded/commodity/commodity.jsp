<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
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
        overflow: hidden;
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
            <span>当前位置>在线购物</span>
            <c:choose>
                <c:when test="${sessionScope.admin}">
                    <div class="bottom fr">
                        <a href="commodity/commodityUpdate.jsp">添加商品</a>
                    </div>
                </c:when>
            </c:choose>
        </div>
        <div class="unit-information">
            <div class="unit">
                <p class="unit-content">商品信息</p>
            </div>
        </div>
        <div class="search bar1" align="center">
            <form action="<c:url value="/commodityServlet"/>" method="post">
                <input type="hidden" name="type" value="query">
                <input type="text" placeholder="输入ID/商品名        (模糊搜索）" name="condition"/>
                <input type="submit" value="搜索" align="center">
            </form>
        </div>
        <br>
        <div class="table-container">
            <table class=table11_6 align="center">
                <tr>
                    <th>ID</th><th>商品名</th><th>单价</th><th>商品介绍</th><th>操作</th>
                </tr>
                <c:forEach items="${requestScope.list}" var="commodity">
                    <tr>
                        <td class="id">${commodity.commodity_id}</td>
                        <td class="name">${commodity.commodity_name}</td>
                        <td class="price">${commodity.price}</td>
                        <td class="introduce">${commodity.introduce}</td>
                        <c:choose>
                            <c:when test="${sessionScope.admin}">
                                <td class="operate">
                                    <a href="<c:url value="/commodityServlet?type=queryById&commodity_id=${commodity.commodity_id}"/>" class="table-view">修改</a>
                                    <a>/</a>
                                    <a href="JavaScript:deleteCommodity(${commodity.commodity_id})" class="table-view">删除</a>
                                </td>
                            </c:when>
                            <c:otherwise>
                                <td class="operate">
                                    <a href="JavaScript:buyingCommodity('${commodity.commodity_id}','${sessionScope.loginUser.user_id}')" class="table-view">购买</a>
                                </td>
                            </c:otherwise>
                        </c:choose>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <br>
    </div>
</div>
</div>
</body>
<script>
    function deleteCommodity(commodity_id){
        if(window.confirm("确定要删除id为"+commodity_id+"的商品吗？")){
            location.href = "<c:url value='/commodityServlet'/>?type=delete&commodity_id="+commodity_id;
        }
    }
    function buyingCommodity(commodity_id,user_id){
        var quantity = prompt("请输入购买商品"+commodity_id+"的数量:", "1"); // "1" 是默认值，可以根据需求设置

        if (quantity != null) {
            // 用户输入了数量
            // 可以将数量和商品ID发送到服务器
            if(window.confirm("确定要购买id为"+commodity_id+"的商品吗？")){
                location.href = "<c:url value='/commodityServlet'/>?type=buying&Commodity_id=" + commodity_id + "&count=" + quantity + "&user_id=" + user_id;
            }
        } else {
            // 用户点击了取消按钮
            alert("取消购买");
        }
    }
</script>
</html>