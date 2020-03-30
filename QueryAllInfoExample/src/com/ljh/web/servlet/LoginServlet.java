package com.ljh.web.servlet;

import com.ljh.domain.User;
import com.ljh.service.UserService;
import com.ljh.service.impl.UserServiceImpl;
import jdk.swing.interop.SwingInterOpUtils;
import org.apache.commons.beanutils.BeanUtils;

import javax.print.attribute.standard.OrientationRequested;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取验证码
        String checkCode = req.getParameter("checkCode");
        HttpSession session = req.getSession();
        if(!checkCode.equalsIgnoreCase(String.valueOf(session.getAttribute("checkCode"))))
        {
            req.setAttribute("checkCode_error","验证码错误");
            req.getRequestDispatcher("/login.jsp").forward(req,resp);
            System.out.println("222");
            return;
        }

        //如果验证码正确
        System.out.println("111");
        Map<String,String[]> map = req.getParameterMap();//不懂
        User loginUser = new User();
        try {
            BeanUtils.populate(loginUser,map); //不懂
            //调用查询函数，返回一个完整的User
            User user = new UserServiceImpl().login(loginUser);
            if(user == null)
            {
                //查询失败
                System.out.println("用户名或者密码错误");
                req.setAttribute("U_P_error","用户名或者密码错误");
                req.getRequestDispatcher("/login.jsp").forward(req,resp);
                System.out.println("222");
                return;
            }

            session.setAttribute("user",user);
            resp.sendRedirect(req.getContextPath()+"/index.jsp");


        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
