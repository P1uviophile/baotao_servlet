package com.system.dao;

import com.system.bean.User;

import java.util.List;

/*
        用户持久层接口
 */
public interface IUserDao {

    /*
            根据用户信息查询用户
     */
    public List<User> list(User user);

    public List<User> listCondition(String str);

    /*
           保存用户信息
     */
    public Integer save(User user);

    /**
     * 删除用户信息
     */
    public Integer deleteByUser_id(Integer user_id);

    /**
     * 根据用户id单条查询用户信息
     */
    public User queryByUser_id(Integer user_id);

    /**
     * 更新用户信息
     */

    public Integer updateUser(User user, String user_name);

    /*
               认证检查--登录功能
         */
    public User checkUserNameAndPassword(String userName,String password);

    boolean checkUserName(String userName, String password);
}
