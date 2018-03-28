<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <title>上传图片</title>
</head>
<body>
<form action="<%=basePath%>/uploadPic" class="form-horizontal" method="post" enctype="multipart/form-data">
    <label>上传图片： </label>
    <input type="file" id="file" name="file">
    <button type="submit">发布</button>
</form>
</body>
</html>