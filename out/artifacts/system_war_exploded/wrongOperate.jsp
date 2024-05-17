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
        操作失败QAQ
        <br><br>
        请检查您输入的数据是否正确
        <br><br>
        请不要输入重复的用户名,请不要输入不存在/重复的ID!
        <br><br>
        购买数,单价,总价不能为负数!
        <br><br>
        点击下面按钮返回上一页面
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