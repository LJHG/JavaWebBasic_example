package cn.ljh.test;

import cn.ljh.dao.UserDao;
import cn.ljh.domain.User;
import org.junit.Test;

public class UserLoginDaotest {

    @Test
    public void testLogin(){
        User loginuser = new User();
        loginuser.setUsername("ljhh");
        loginuser.setPassword("123456");

        UserDao dao = new UserDao();
        User user = dao.login(loginuser);
        System.out.println(user);
    }


}
