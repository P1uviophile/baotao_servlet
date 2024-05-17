package com.system.dao.impl;

import com.system.dao.IUserDao;
import com.system.bean.User;
import com.system.utils.MyDBUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.*;

/*
        用户dao接口的实现类
 */
public class UserDaoImpl implements IUserDao {
    @Override
    public List<User> list(User user) {
        // 通过DBUtils来实现数据库的操作
        QueryRunner queryRunner = MyDBUtils.getQueryRunner();
        // 构建SQL语句
        String test = "select * from user";
        List<User> list = null;
        try {
            list = queryRunner.query(test, new BeanListHandler<User>(User.class));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    @Override
    public List<User> listCondition(String str) {
        // 1.获取QueryRunner对象
        QueryRunner queryRunner = MyDBUtils.getQueryRunner();
        String search_id = "select * from user where user_id like ?";
        String search_name = "select * from user where user_name like ?";
        List<User> list = null;
        try {
            // 添加通配符 % 到参数值中
            String searchTerm = "%" + str + "%";
            list = queryRunner.query(search_id, new BeanListHandler<User>(User.class), searchTerm);
            list.addAll(queryRunner.query(search_name, new BeanListHandler<User>(User.class), searchTerm));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Set<User> set = new TreeSet<>(Comparator.comparing(User::getUser_id));
        set.addAll(list);
        return new ArrayList<>(set);
    }

    // 添加用户的方法
    @Override
    public Integer save(User user) {
        // 1.获取QueryRunner对象
        QueryRunner queryRunner = MyDBUtils.getQueryRunner();
        String sql = "insert into user(user_name,password,admin)values(?,?,?)" ;
        System.out.println("saving");
        try {
            return queryRunner.update(sql,
                    user.getUser_name(),
                    user.getPassword(),
                    user.getAdmin());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public Integer deleteByUser_id(Integer user_id) {
        QueryRunner queryRunner = MyDBUtils.getQueryRunner();
        String sql = "delete from user where user_id = ?" ;
        try {
            return queryRunner.update(sql, user_id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public User queryByUser_id(Integer user_id) {
        QueryRunner queryRunner = MyDBUtils.getQueryRunner();
        String search_id = "select * from user where user_id = ?";
        try {
            return queryRunner.query(search_id, new ResultSetHandler<User>() {
                @Override
                public User handle(ResultSet resultSet) throws SQLException {
                    if (resultSet.next()){
                        User user = new User();
                        user.setUser_id(user_id);
                        user.setUser_name(resultSet.getString("user_name"));
                        user.setPassword(resultSet.getString("password"));
                        return user;
                    }
                    return null;
                }
            }, user_id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Integer updateUser(User user, String user_name) {
        QueryRunner queryRunner = MyDBUtils.getQueryRunner();
        String sql = "update user set user_name = ? , password = ?, admin = ? where user_id = ?" ;
        if (user_name.equals(user.getUser_name())){
            System.out.println(user_name+" "+user.getUser_name());
            sql = "update user set password = ?,admin = ? where user_name = ?";
            try {
                return queryRunner.update(sql,
                        user.getPassword(),
                        user.getAdmin(),
                        user.getUser_name());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else {
            try {
                return queryRunner.update(sql,
                        user.getUser_name(),
                        user.getPassword(),
                        user.getAdmin(),
                        user.getUser_id());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    @Override
    public User checkUserNameAndPassword(String userName, String password) {

        QueryRunner queryRunner = MyDBUtils.getQueryRunner();
        String sql = "select * from user where user_name=? and password=?";
        System.out.println("Query: " + sql);
        System.out.println("Username: " + userName);
        System.out.println("Password: " + password);
        try {
            return queryRunner.query(sql, new ResultSetHandler<User>() {
                @Override
                public User handle(ResultSet resultSet) throws SQLException {
                    User user = null;
                    if (resultSet.next()) {
                        user = new User();
                        user.setUser_id(resultSet.getInt("user_id"));
                        user.setUser_name(resultSet.getString("user_name"));
                        user.setPassword(resultSet.getString("password"));
                        user.setAdmin(resultSet.getInt("admin"));
                        return user;
                    }
                    return null;
                }
            }, userName, password);
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean checkUserName(String userName, String password) {

        QueryRunner queryRunner = MyDBUtils.getQueryRunner();
        String sql = "select count(*) from user where user_name=? and password=?";
        System.out.println("Query: " + sql);
        System.out.println("Username: " + userName);
        System.out.println("Password: " + password);
        try {
            Long count = queryRunner.query(sql, new ScalarHandler<Long>(), userName, password);
            return count != null && count > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    // 测试
    public static void main(String[] args){
        UserDaoImpl dao = new UserDaoImpl();
        for (User user : dao.list(null)) {
            System.out.println(user);
        }
    }
}