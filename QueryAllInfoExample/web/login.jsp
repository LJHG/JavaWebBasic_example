<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/3/17
  Time: 18:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login 2.0 using jsp and add checkcode</title>
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css" type="text/css">
    <script>
        window.onload = function () {
            var checkCode = document.getElementById("checkCode");
            checkCode.onclick = function () {
                this.src = "${pageContext.request.contextPath}/checkCode?time="+new Date().getTime(); //needs to new all the time
            }
        }
    </script>
</head>
<body>
<div class="container">
    <h1 align="center">管理员登陆界面</h1>
    <div align="center">
        <form method="post" action = "${pageContext.request.contextPath}/loginServlet">
            用户名： <input type="text" name = "username"> <br>
            密码： <input type="password" name = "password"> <br>
            验证码： <input type="text" name = "checkCode"> <br>
            <img src="${pageContext.request.contextPath}/checkCode" id="checkCode"><br>
            <input type="submit" value="登陆">
        </form>

        <div>${U_P_error}</div>
        <div>${checkCode_error}</div>
    </div>
</div>

</body>
</html>