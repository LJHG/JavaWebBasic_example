package com.ljh.service.impl;

import com.ljh.dao.UserDao;
import com.ljh.dao.impl.UserDaoImpl;
import com.ljh.domain.PageBean;
import com.ljh.domain.User;
import com.ljh.service.UserService;

import java.util.List;
import java.util.Map;

public class UserServiceImpl implements UserService {


    @Override
    public List<User> findAll() throws Exception {
        //调用dao完成查询
        UserDao userListDao = new UserDaoImpl();
        List<User> users = userListDao.findAll();
        return users;
    }

    @Override
    public User login(User loginUser) throws Exception {
        User user = new UserDaoImpl().getUserInfoByUsernameAndPassword(loginUser.getUsername(),loginUser.getPassword());
        return user;
    }

    @Override
    public void addUser(User addUser) throws Exception {
        //调用dao里的add操作
        UserDao userDao = new UserDaoImpl();
        userDao.add(addUser);
    }

    @Override
    public void deleteUser(String id) throws Exception {
        int intID = Integer.parseInt(id);
        UserDao userDao = new UserDaoImpl();
        userDao.deleteById(intID);
    }

    @Override
    public User findUser(String id) throws Exception {
        int intID = Integer.parseInt(id);
        UserDao userDao = new UserDaoImpl();
        User user = userDao.findById(intID);
        return user;
    }

    @Override
    public void updateUserInfo(User user) throws Exception {
        new UserDaoImpl().updateUserInfo(user);
    }

    @Override
    public void deleteSelected(String[] ids) throws Exception {
        for (String id : ids) {
            new UserDaoImpl().deleteById(Integer.parseInt(id));
        }
    }

    @Override
    public PageBean<User> getPageUsersBean(int currentPage, int rows, Map<String, String[]> condition) throws Exception {

        UserDaoImpl userDao = new UserDaoImpl();
        PageBean<User> pb = new PageBean<User>();
        pb.setRows(rows);

        //get totalNums from db
        int totalNums = userDao.getTotalNums(condition);
        System.out.println(totalNums);
        pb.setTotalNums(totalNums);

        //set total page
        int totalPages = totalNums % rows == 0? totalNums/rows : totalNums/rows+1;
        pb.setTotalPage(totalPages);

        if(currentPage <= 0 )
                currentPage =1;
        if(currentPage >= totalPages)
                currentPage = totalPages;
        pb.setCurrentPage(currentPage);

        //get current info from db
        List<User> currentInfo = userDao.getCurrentInfo(currentPage,rows,condition);
        pb.setCurrentInfo(currentInfo);



        return pb;
    }
}
