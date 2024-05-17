<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <style>
  #frameTop
  {
  position: fixed;
  top: 0;
  left: 0;
  height: 89px;
  width: 100%;
  overflow: hidden;
  vertical-align: text-top;
  }

  #frameContentLeft
  {
  position: fixed;
  top: 89px;
  left: 0;
  height: 100%;
  width: 200px;
  overflow: hidden;
  vertical-align: top;
  background-color: #5582bb;
  }

  #frameContentRight
  {
  position: fixed;
  left: 200px;
  top: 89px;
  height: 90%;
  width: 87%;
  right: 0;
  bottom: 0;
  overflow: auto;
  background: #fff;
  }
  </style>
  <title></title>
</head>

<div>
  <iframe id="frameTop" src="top.jsp"></iframe>
  <div>
    <iframe id="frameContentLeft" src="Left.jsp" ></iframe>
    <iframe id="frameContentRight" src="Right.jsp" name="main" ></iframe>
  </div>
</div>


</html>
