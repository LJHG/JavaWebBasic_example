package com.ljh.dao.impl;

import com.ljh.dao.UserDao;
import com.ljh.domain.User;
import com.ljh.utils.jdbcUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserDaoImpl implements UserDao {

    private JdbcTemplate jdbcTemplate  = new JdbcTemplate(jdbcUtils.getDataSource());

    public UserDaoImpl() throws Exception {

    }

    @Override
    public List<User> findAll() throws Exception {

        String sql = "select * from user";
        //这一行太猛了
        List<User> users = jdbcTemplate.query(sql,new BeanPropertyRowMapper<User>(User.class));

        return users;
    }

    @Override
    public User getUserInfoByUsernameAndPassword(String username, String password) throws Exception {

        String sql = "select * from user where username=? and password=?";

        //遇事不决RowMapper
        //这里如果查出来是空会抛异常
        try {
            User user  = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), new String[]{username, password});
            return user;
        }
        catch(Exception e) {
            return null;
        }
    }

    @Override
    public void add(User addUser) throws Exception {
        String sql = "insert into user values(null,?,?,?,?,?,?,null,null)";

        jdbcTemplate.update(sql,
                addUser.getName(),
                addUser.getAge(),
                addUser.getGender(),
                addUser.getAddress(),
                addUser.getQq(),
                addUser.getEmail());
    }

    @Override
    public void deleteById(int id) throws Exception {
        String sql = "delete from user where id=?";
        jdbcTemplate.update(sql,id);
    }

    @Override
    public User findById(int id) throws Exception {
        String sql = "select * from user where id =?";
        User user = jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<User>(User.class),id);
        return user;
    }

    @Override
    public void updateUserInfo(User user) throws Exception {
        String sql = "update user set name=?, age=?, gender=?, address=?, qq=?, email=? where id=?";
        jdbcTemplate.update(sql,
                user.getName(),
                user.getAge(),
                user.getGender(),
                user.getAddress(),
                user.getQq(),
                user.getEmail(),
                user.getId()
                );
    }

    @Override
    public int getTotalNums(Map<String, String[]> condition) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append("select count(*) from user where 1=1");

        List<Object> parameterList = new ArrayList<Object>();

        for (Map.Entry<String, String[]> stringEntry : condition.entrySet()) {
            if(stringEntry.getKey().equals("currentPage") || stringEntry.getKey().equals("rows"))
            {
               continue;
            }
            if(stringEntry.getValue()[0] == null || stringEntry.getValue()[0].equals(""))
            {
                continue;
            }
            sb.append(" and "+stringEntry.getKey()+" like ?");
            parameterList.add("%"+stringEntry.getValue()[0]+"%");
        }


        String sql = sb.toString();

        System.out.println(sql);
        System.out.println(parameterList);

        return jdbcTemplate.queryForObject(sql,Integer.class,parameterList.toArray());
    }

    @Override
    public List<User> getCurrentInfo(int currentPage, int rows, Map<String, String[]> condition) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append("select * from user where 1=1");

        List<Object> parameterList = new ArrayList<Object>();

        for (Map.Entry<String, String[]> stringEntry : condition.entrySet()) {
            if(stringEntry.getKey().equals("currentPage") || stringEntry.getKey().equals("rows"))
            {
                continue;
            }
            if(stringEntry.getValue()[0] == null || stringEntry.getValue()[0].equals(""))
            {
                continue;
            }
            sb.append(" and "+stringEntry.getKey()+" like ?");
            parameterList.add("%"+stringEntry.getValue()[0]+"%");
        }

        sb.append(" limit ?,?");
        parameterList.add((currentPage-1)*rows);
        parameterList.add(rows);

        String sql = sb.toString();

        try {
            List<User> currentInfo = jdbcTemplate.query(sql,new BeanPropertyRowMapper<User>(User.class),parameterList.toArray());
            return currentInfo;
        }
        catch (Exception e)
        {
            return null;
        }

    }
}
