<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/3/13
  Time: 20:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login 2.0 using jsp and add checkcode</title>
    <script>
        window.onload = function () {
            var checkCode = document.getElementById("checkCode");
            checkCode.onclick = function () {
                this.src = "/checkCode?time="+new Date().getTime(); //needs to new all the time
            }
        }
    </script>
</head>
<body>
    <form method="post" action = "/loginServlet">
        用户名： <input type="text" name = "username"> <br>
        密码： <input type="password" name = "password"> <br>
        验证码： <input type="text" name = "checkCode"> <br>
        <img src="/checkCode" id="checkCode"><br>
        <input type="submit" value="登陆">
    </form>
    <div> <%= request.getSession().getAttribute("cc_error") == null?"":request.getSession().getAttribute("cc_error")%></div>
    <div> <%= request.getSession().getAttribute("userOrpassword_error")==null?"":request.getSession().getAttribute("userOrpassword_error")%></div>
</body>
</html>
