import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.DataInput;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;


@WebServlet("/cookieTest")
public class CookieTest extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        Cookie[] cookies = req.getCookies();
        System.out.println(cookies.length);
        boolean flag = false;

        if(cookies != null && cookies.length != 0)
        {
            for (Cookie cookie : cookies) {
                if(cookie.getName().equals("lasttimeLogin")){ //can not use ==
                    String logintime = cookie.getValue();
                    System.out.println(logintime);
                    String decodeLogintime = URLDecoder.decode(logintime,"utf-8");
                    resp.getWriter().write("<h1>your last login time is:"+decodeLogintime+"</h1>");

                    Date currentDate = new Date();
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年-MM月-dd日 HH:mm:ss");
                    String currentTime = simpleDateFormat.format(currentDate.getTime());


                    //这里不能直接把currenttime传给setvalue，不然set不了，也不抛出错误，太坑了。
                    //要先encode 然后再setvalue
                    System.out.println(currentTime);
                    String encodeTime = URLEncoder.encode(currentTime,"utf-8");
                    cookie.setValue(encodeTime);
                    cookie.setMaxAge(60*60*24*30);

                    resp.addCookie(cookie);
                    flag = true;
                    break;
                }
            }
        }


        if(flag == false || cookies.length == 0 || cookies == null)
        {
            //dont have the cookie named lasttimeLogin
            resp.getWriter().write("<h1>this is your first login, welcom~</h1>");
            Date date = new Date();

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String time = simpleDateFormat.format(date.getTime());

            Cookie cookie = new Cookie("lasttimeLogin",time);
            cookie.setMaxAge(60*60*24*30);
            resp.addCookie(cookie);
            System.out.println("first login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
