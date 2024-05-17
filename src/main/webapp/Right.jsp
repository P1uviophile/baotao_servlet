<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title></title>
    <style>
        .background{
            width: 100%;
            height: 100%;
            background: url("images/worongOperate.png");
            background-size:cover;
        }
    </style>
</head>
<body>
<div class="background" style="margin: 0 auto;">
    <div align="center" style="margin: 0 auto;">
        <br><br><br><br><br><br><br><br><br><br><br><br>

        欢迎光临在线购物网站!
        <br><br><br><br><br><br><br><br>
        现在是
        <div id="datetime">
            <script>
                setInterval("document.getElementById('datetime').innerHTML=new Date().toLocaleString();", 1000);
            </script>
        </div>
        <br><br><br><br><br><br><br><br><br><br><br><br>
    </div>
</div>
</body>
<script>
    function jump(){
        //window.location=document.referrer;
    }
</script>
</html>
