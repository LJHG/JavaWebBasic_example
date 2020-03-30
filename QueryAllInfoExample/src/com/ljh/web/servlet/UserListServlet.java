package com.ljh.web.servlet;

import com.ljh.domain.User;
import com.ljh.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet("/userListServlet")
public class UserListServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            //调用service里面的findAll函数，得到List集合
            List<User> users = new UserServiceImpl().findAll();
            //把List集合加入request域
            req.setAttribute("users",users);
            //转发到info.jsp
            req.getRequestDispatcher("/info.jsp").forward(req,resp);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
