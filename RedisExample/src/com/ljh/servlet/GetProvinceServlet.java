package com.ljh.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ljh.domain.Province;
import com.ljh.service.ProvinceService;
import com.ljh.service.impl.ProvinceServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/getProvinceServlet")
public class GetProvinceServlet extends HttpServlet {

    private ProvinceService provinceService;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            provinceService = new ProvinceServiceImpl();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {

            String json = provinceService.getAllProvince_redis();
            //输出
            resp.setContentType("application/json;charset=utf-8");
            resp.getWriter().write(json);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
