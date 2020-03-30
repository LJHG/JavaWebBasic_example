package com.ljh.service;

import com.ljh.domain.PageBean;
import com.ljh.domain.User;

import java.util.List;
import java.util.Map;

public interface UserService {

    public List<User> findAll() throws Exception;
    public User login(User loginUser) throws Exception;
    public void addUser(User addUser) throws Exception;
    public void deleteUser(String id) throws Exception;
    public User findUser(String id) throws Exception;
    public void updateUserInfo(User user) throws Exception;
    public void deleteSelected(String[] ids) throws Exception;
    public PageBean<User> getPageUsersBean(int currentPage, int rows, Map<String, String[]> condition) throws Exception;


}
