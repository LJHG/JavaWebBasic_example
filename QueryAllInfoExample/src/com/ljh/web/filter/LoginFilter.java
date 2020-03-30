package com.ljh.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.http.HttpRequest;


@WebFilter("/*")
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;

        String uri = req.getRequestURI();

        System.out.println(uri);
        if(uri.contains("/login.jsp") || uri.contains("/loginServlet") || uri.contains("/bootstrap/") || uri.contains("/checkCode")){
            //就是要登陆，直接放行
            filterChain.doFilter(req,servletResponse);
        }
        else {
            if(req.getSession().getAttribute("user") != null)
            {
                //已经登录
                filterChain.doFilter(req,servletResponse);
            }

            else {
                //没登陆去登陆
                req.getRequestDispatcher("/login.jsp").forward(req,servletResponse);
            }

        }


    }

    @Override
    public void destroy() {

    }
}
