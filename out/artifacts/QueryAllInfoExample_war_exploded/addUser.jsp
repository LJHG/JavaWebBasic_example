<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/3/18
  Time: 14:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css" type="text/css">
    <title>添加用户</title>
</head>
<body>
    <div class="container">
        <h1 align="center">填写要添加用户的信息</h1>
        <form action="${pageContext.request.contextPath}/addUserServlet" method="post">
            <div class="form-group">
                <label for="name">姓名</label>
                <input type="text" class="form-control" id="name"  name="name">
            </div>
            <div class="form-group">
                <label for="age">年龄</label>
                <input type="text" class="form-control" id="age"  name="age">
            </div>
            <div class="checkbox">
                性别:
                <label>
                    <input type="radio" name="gender" value="male"> 男
                    <input type="radio" name="gender" value="female"> 女
                </label>
            </div>
            <div class="form-group">
                <label for="addr">地址</label>
                <input type="text" class="form-control" id="addr"  name="address">
            </div>
            <div class="form-group">
                <label for="qq">qq</label>
                <input type="text" class="form-control" id="qq"  name="qq">
            </div>
            <div class="form-group">
                <label for="email">邮箱</label>
                <input type="email" class="form-control" id="email" name="email">
            </div>
            <button type="submit" class="btn btn-default">Submit</button>
        </form>
    </div>
</body>
</html>
