<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/3/16
  Time: 19:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
    <h2>Welcome,${sessionScope.user.name}</h2>
    <a href="${pageContext.request.contextPath}/findUserByPageServlet?currentPage=1&rows=5">查询所有成员的信息</a>
  </body>
</html>
