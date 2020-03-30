package cn.ljh.download;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


@WebServlet("/downloadFile")
public class FileDownloadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String filename = req.getParameter("filename");
        //获取文件的真实路径
        ServletContext context = getServletContext();
        String realPath = context.getRealPath("/img/"+filename); //在web目录下，用/
        System.out.println(realPath);
        //获取Mime类型
        String mime = context.getMimeType(filename);

        //设置content-type 和 content-disposition
        resp.setContentType(mime);
        resp.setHeader("content-disposition","attachment;filename="+filename);

        //获取输入流 然后把输入流给输出流
        FileInputStream fis = new FileInputStream(realPath);
        ServletOutputStream sos = resp.getOutputStream();
        byte[] buffer = new byte[1024*100];
        int len =0;
        while( (len = fis.read(buffer)) != -1)
            sos.write(buffer,0,len);
        fis.close();

    }
}
