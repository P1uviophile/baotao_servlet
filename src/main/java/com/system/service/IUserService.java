package com.system.service;

import com.system.bean.User;

import java.util.List;

/**
 * 用户的service接口
 */
public interface IUserService {

    public List<User> getUser(User user);

    public List<User> getUserCondition(String str);

    public Integer addUser(User user);

    public Integer deleteByUser_id(Integer user_id);

    public User queryByUser_id(Integer user_id);

    public Integer updateUser(User user,String user_name);

    public User checkUserNameAndPassword(String userName,String password);

    boolean checkUserName(String userName, String password);
}
