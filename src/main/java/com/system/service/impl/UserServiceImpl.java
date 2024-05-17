package com.system.service.impl;

import com.system.bean.User;
import com.system.dao.IUserDao;
import com.system.dao.impl.UserDaoImpl;
import com.system.service.IUserService;

import java.util.List;

/**
 * 用户Service接口的实现
 * 通过调用Dao来实现复杂的业务逻辑处理
 */
public class UserServiceImpl implements IUserService {

    private IUserDao userDao = new UserDaoImpl();

    @Override
    public List<User> getUser(User user) {
        // 处理对应的业务逻辑
        return userDao.list(user);
    }

    @Override
    public List<User> getUserCondition(String str) {
        // 处理对应的业务逻辑
        return userDao.listCondition(str);
    }

    @Override
    public Integer addUser(User user) {
        return userDao.save(user);
    }

    @Override
    public Integer deleteByUser_id(Integer user_id) {
        return userDao.deleteByUser_id(user_id);
    }

    @Override
    public User queryByUser_id(Integer user_id) {
        return userDao.queryByUser_id(user_id);
    }

    @Override
    public Integer updateUser(User user,String user_name) {
        return userDao.updateUser(user,user_name);
    }

    @Override
    public User checkUserNameAndPassword(String userName, String password) {
        User user = userDao.checkUserNameAndPassword(userName,password);
        System.out.println(user);
        return user;
    }

    @Override
    public boolean checkUserName(String userName, String password) {
        return userDao.checkUserName(userName,password);
    }
}
