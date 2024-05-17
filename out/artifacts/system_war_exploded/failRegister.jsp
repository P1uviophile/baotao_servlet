<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<style>
    .background{
        width: 100%;
        height: 100%;
        background: url("images/worongOperate.png");
        background-size:100% 100%;
    }
</style>
<body onload="window.setTimeout(count(),3000)">
<div class="background">
    <div align="center">
        <br><br><br><br><br><br><br>
        注册失败QAQ
        <br><br>
        用户名重复
        <br><br>
        点击下面按钮返回注册页面
        <br><br>
        <input class="btn btn-default" onclick="backAndFresh()" type="button" value="返回" />
    </div>
</div>
</body>
<script language="javascript" type="text/javascript">
    function backAndFresh(){
        window.location=document.referrer;
    }
</script>
</html>