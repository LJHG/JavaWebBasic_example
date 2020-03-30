package com.ljh.dao;

import com.ljh.domain.User;

import java.util.List;
import java.util.Map;

public interface UserDao {
    public List<User> findAll() throws Exception;
    public User getUserInfoByUsernameAndPassword(String username,String password) throws Exception;
    public void add(User addUser) throws Exception;
    public void deleteById(int id) throws Exception;
    public User findById(int id) throws Exception;
    public void updateUserInfo(User user) throws Exception;
    public int getTotalNums(Map<String, String[]> condition) throws Exception;
    public List<User> getCurrentInfo(int currentPage, int rows, Map<String, String[]> condition) throws Exception;
}
