<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="<c:url value="/css/common.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/main.css"/>">
</head>
<body>
<div class="container">
    <div class="content" style="margin: auto">
        <div class="information-title">
            <span>当前位置>订单信息>订单表单</span>
            <div class="bottom fr">

            </div>
        </div>

        <div class="unit-information">
            <div class="unit">
                <p class="unit-content">订单信息</p>
            </div>
            <div class="unit-list clearfix">
                <div class="fl">
                    <form action="<c:url value="/ordersServlet"/>" method="post">
                        <div class="list ">
                            <input type="hidden" name="type" value="save">
                            <c:if test="${not empty orders}">
                                <input type="hidden" name="order_id" value="${orders.order_id}">
                            </c:if>
                            <c:if test="${sessionScope.admin == false}">
                                <input type="hidden" name="client_id" value="${orders.client_id}"/>
                                <input type="hidden" name="Commodity_id" value="${orders.commodity_id}"/>
                                <input type="hidden" name="price" value="${orders.price}"/>
                                <input type="hidden" name="count" value="${orders.count}"/>
                            </c:if>
                            <c:if test="${sessionScope.admin}">
                                <script type="text/javascript">
                                    clearHiddenFields();
                                </script>
                                <label>客户ID</label> : <input type="text" name="client_id" value="${orders.client_id}"/>
                                <label>报纸ID</label> : <input type="text" name="Commodity_id" value="${orders.commodity_id}"/>
                                <br><br>
                                <label>订单总价</label> : <input type="text" name="price" value="${orders.price}"/>
                                <label>商品数</label> : <input type="text" name="count" value="${orders.count}"/>
                                <br><br>
                            </c:if>
                            <label>订单结束状态</label> :
                            <input name="finish" type="radio" value="1" checked="checked">是
                            <input name="finish" type="radio" value="0">否
                        </div>
                        <label>&nbsp</label><input type="submit" value="确认" class="list" >
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    function clearHiddenFields() {
        // 清除隐藏字段的值
        document.getElementsByName("client_id")[0].value = "";
        document.getElementsByName("Commodity_id")[0].value = "";
        document.getElementsByName("price")[0].value = "";
        document.getElementsByName("count")[0].value = "";
    }
</script>
</body>
</html>

