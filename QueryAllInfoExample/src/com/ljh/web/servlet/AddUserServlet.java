package com.ljh.web.servlet;

import com.ljh.domain.User;
import com.ljh.service.UserService;
import com.ljh.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/addUserServlet")
public class AddUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取输入结果
        Map<String,String[]> map = req.getParameterMap();
        User addUser = new User();
        try {
            BeanUtils.populate(addUser,map);
            //调用service里的addUser函数
            UserService service= new UserServiceImpl();
            service.addUser(addUser);
            //添加好后，重新查询
            resp.sendRedirect(req.getContextPath()+"/findUserByPageServlet?currentPage=1&rows=5");

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }
}
