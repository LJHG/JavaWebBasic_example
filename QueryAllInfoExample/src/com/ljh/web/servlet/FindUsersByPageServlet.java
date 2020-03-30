package com.ljh.web.servlet;

import com.ljh.domain.PageBean;
import com.ljh.domain.User;
import com.ljh.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/findUserByPageServlet")
public class FindUsersByPageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int currentPage = Integer.parseInt(req.getParameter("currentPage"));
        int rows = Integer.parseInt(req.getParameter("rows"));

        Map<String,String[]> condition = req.getParameterMap();


        try {
            //调用service的getPageBean方法来获得pageBean对象
            PageBean<User> pb = new UserServiceImpl().getPageUsersBean(currentPage,rows,condition);
            //把pageBean对象传入request域中
            req.setAttribute("pb",pb);
            req.setAttribute("condition",condition);
            req.getRequestDispatcher("/info.jsp").forward(req,resp);

        } catch (Exception e) {
            e.printStackTrace();
        }



    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
