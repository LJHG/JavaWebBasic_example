package cn.ljh.dao;

import cn.ljh.domain.User;
import cn.ljh.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

//操作数据库中user表的类
public class UserDao {


    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    public User login(User loginUser){

        User user = null;
        try {
            String sql = "select * from user where username = ? and password = ?";

            user = template.queryForObject(sql,
                    new BeanPropertyRowMapper<User>(User.class),
                    loginUser.getUsername(),
                    loginUser.getPassword());
            return user;
        } catch (DataAccessException e) {
            //e.printStackTrace();
            return null;
        }



    }


}
