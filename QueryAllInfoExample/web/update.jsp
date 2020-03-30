<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/3/18
  Time: 14:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css" type="text/css">
    <title>添加用户</title>
</head>
<body>
<div class="container">
    <h1 align="center">修改用户信息</h1>
    <form action="${pageContext.request.contextPath}/updateUserServlet" method="post">
        <input hidden name="id" value="${user.id}">
        <div class="form-group">
            <label for="name">姓名</label>
            <input type="text" class="form-control" id="name"  name="name" value="${user.name}">
        </div>
        <div class="form-group">
            <label for="age">年龄</label>
            <input type="text" class="form-control" id="age"  name="age" value="${user.age}">
        </div>

        <div class="checkbox">
            性别:
            <label>
                <input type="radio" name="gender" value="male" <c:if test="${user.gender == 'male'}">checked</c:if>> 男
                <input type="radio" name="gender" value="female" <c:if test="${user.gender == 'female'}">checked</c:if>> 女
            </label>
        </div>
        <div class="form-group">
            <label for="addr">地址</label>
            <input type="text" class="form-control" id="addr"  name="addr" value="${user.address}">
        </div>
        <div class="form-group">
            <label for="qq">qq</label>
            <input type="text" class="form-control" id="qq"  name="qq" value="${user.qq}">
        </div>
        <div class="form-group">
            <label for="email">邮箱</label>
            <input type="email" class="form-control" id="email" name="email" value="${user.email}">
        </div>
        <button type="submit" class="btn btn-default">确认修改</button>
    </form>
</div>
</body>
</html>
