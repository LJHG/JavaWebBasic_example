<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/3/16
  Time: 19:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css" type="text/css">
<%--    <link href="http://libs.baidu.com/bootstrap/3.0.3/css/bootstrap.min.css" rel="stylesheet" />--%>
    <script>
        function deleteUser(id){
            if(confirm("确定要删除吗?"))
            {
                location.href = "${pageContext.request.contextPath}/deleteUserServlet?id="+id;
            }
        }
        window.onload = function () {

            //实现全选反选
            var firstCb = document.getElementById("firstCb");
            firstCb.onclick = function () {
                var cbs = document.getElementsByName("cb");
                for(var i =0; i<cbs.length ;i++)
                {
                    cbs[i].checked = firstCb.checked;
                }
            }


            //实现点击删除选中按钮实现提交表单
            var deleteSelectedButton = document.getElementById("deleteSelectedButton");


            deleteSelectedButton.onclick= function () {
                var cbs = document.getElementsByName("cb");
                var flag = false;
                for(var i=0 ;i<cbs.length;i++)
                {
                    if(cbs[i].checked){
                        flag=true;
                    }
                }
                if(flag==false)
                {
                    alert("选中为空！")
                }
                else
                {
                    if(confirm("你真的要删除选中的所有条目吗?")){
                        document.getElementById("deleteIdsForm").submit();
                    }
                }



            }



        }

    </script>
</head>
<body>
<div class="container">
    <h1 align="center">用户信息查询结果</h1>
    <div style="float: left">
        <form class="form-inline" method="post" action="${pageContext.request.contextPath}/findUserByPageServlet?currentPage=1&rows=5">
            <div class="form-group">
                <label for="name">姓名</label>
                <input type="text" class="form-control" id="name" name = "name" value="${condition.name[0]}">
            </div>
            <div class="form-group">
                <label for="addr">籍贯</label>
                <input type="text" class="form-control" id="addr" name = "address" value="${condition.address[0]}">
            </div>
            <div class="form-group">
                <label for="email">邮箱</label>
                <input type="text" class="form-control" id="email" name = "email" value="${condition.email[0]}">
            </div>
            <button type="submit" class="btn btn-default">提交</button>
        </form>
    </div>
    <div style="float: right">

        <a type="button" class="btn btn-primary" href="${pageContext.request.contextPath}/addUser.jsp" >添加联系人</a>
        <a type="button" class="btn btn-primary" id="deleteSelectedButton">删除选中</a>
    </div>

    <%-- 通过在外面套一个表单来实现获取所有选取的id--%>
    <form action="${pageContext.request.contextPath}/deleteSelectedServlet" method="post" id="deleteIdsForm">
        <table class="table table-striped">
            <tr>
                <th><input type="checkbox" name="firstCb" id="firstCb"></th>
                <th>id</th>
                <th>姓名</th>
                <th>年龄</th>
                <th>性别</th>
                <th>地址</th>
                <th>qq</th>
                <th>邮箱</th>
                <th>操作</th>
            </tr>
            <c:forEach items="${pb.currentInfo}" var="uuu" varStatus="s" >
                <%--        这里的var 给什么都可以，但是给item时记得要用el--%>
                <tr>
                    <th><input type="checkbox" name="cb" value="${uuu.id}"></th>
                    <th>${s.count}</th> <%-- 这里用s.count 而不是id--%>
                    <th>${uuu.name}</th>
                    <th>${uuu.age}</th>
                    <th>${uuu.gender}</th>
                    <th>${uuu.address}</th>
                    <th>${uuu.qq}</th>
                    <th>${uuu.email}</th>
                    <th><a class="btn btn-default" href="${pageContext.request.contextPath}/findUserServlet?id=${uuu.id}">修改</a>
                        <a class="btn btn-default" href="javascript:deleteUser(${uuu.id})">删除</a>
                    </th>
                </tr>
            </c:forEach>

        </table>
    </form>

    <nav aria-label="Page navigation">
        <ul class="pagination">
            <li>
                <a href="${pageContext.request.contextPath}/findUserByPageServlet?currentPage=${pb.currentPage -1}&rows=5&name=${condition.name[0]}&address=${condition.address[0]}&email=${condition.email[0]}" aria-label="Previous">
                    <span aria-hidden="true">&laquo;</span>
                </a>
            </li>

            <c:forEach begin="1" end="${pb.totalPage}" var="i" step="1">
                <li <c:if test="${pb.currentPage == i}">class="active"</c:if> <c:if test="${pb.currentPage != i}">class=""</c:if>><a href="${pageContext.request.contextPath}/findUserByPageServlet?currentPage=${i}&rows=5&name=${condition.name[0]}&address=${condition.address[0]}&email=${condition.email[0]}">${i}</a></li>
            </c:forEach>

            <li>
                <a href="${pageContext.request.contextPath}/findUserByPageServlet?currentPage=${pb.currentPage + 1}&rows=5&name=${condition.name[0]}&address=${condition.address[0]}&email=${condition.email[0]}" aria-label="Next">
                    <span aria-hidden="true">&raquo;</span>
                </a>
            </li>

        </ul>
    </nav>
    <span>一共有${pb.totalPage}页,共${pb.totalNums}条数据</span>


</div>

</body>
</html>
