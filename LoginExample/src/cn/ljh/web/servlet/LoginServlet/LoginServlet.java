package cn.ljh.web.servlet.LoginServlet;

import cn.ljh.dao.UserDao;
import cn.ljh.domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String checkCode = req.getParameter("checkCode");
        HttpSession session = req.getSession();
        String checkCode_session = (String) session.getAttribute("checkCode");
        session.removeAttribute("checkCode");

        if(!checkCode.equalsIgnoreCase(checkCode_session))
        {
            //验证码不正确，返回原页面，并把错误信息存进session
            session.setAttribute("cc_error","验证码错误");
            req.getRequestDispatcher("/login.jsp").forward(req,resp);
        }

        else {

            User loginUser = new User();
            loginUser.setUsername(username);
            loginUser.setPassword(password);

            UserDao dao = new UserDao();
            User user = dao.login(loginUser);

            if(user == null)
            {
                //fail
                session.setAttribute("userOrpassword_error","用户或者密码错误");
                req.getRequestDispatcher("/login.jsp").forward(req,resp);
            }
            else {
                //success
                //存储数据
                req.setAttribute("user",user);

                //转发
                req.getRequestDispatcher("/successServlet").forward(req,resp);

            }
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}
