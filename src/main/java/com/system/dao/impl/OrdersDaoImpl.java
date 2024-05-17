package com.system.dao.impl;

import com.system.bean.Orders;
import com.system.dao.IOrdersDao;
import com.system.utils.MyDBUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class OrdersDaoImpl implements IOrdersDao {


    @Override
    public List<Orders> list(Orders orders) {
        // 通过DBUtils来实现数据库的操作
        QueryRunner queryRunner = MyDBUtils.getQueryRunner();
        // 构建SQL语句
        String test = "select * from orders";
        List<Orders> list = null;
        try {
            list = queryRunner.query(test, new BeanListHandler<Orders>(Orders.class));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    @Override
    public List<Orders> listCondition(String str) {
        // 1.获取QueryRunner对象
        QueryRunner queryRunner = MyDBUtils.getQueryRunner();
        String search_id = "select * from orders where order_id like ?";
        String client_id = "select * from orders where client_id like ?";
        String Commodity_id = "select * from orders where commodity_id like ?";
        List<Orders> list = null;
        try {
            // 添加通配符 % 到参数值中
            String searchTerm = str ;
            list = queryRunner.query(search_id, new BeanListHandler<Orders>(Orders.class), searchTerm);
            list.addAll(queryRunner.query(client_id, new BeanListHandler<Orders>(Orders.class), searchTerm));
            list.addAll(queryRunner.query(Commodity_id, new BeanListHandler<Orders>(Orders.class), searchTerm));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Set<Orders> set = new TreeSet<>(Comparator.comparing(Orders::getOrder_id));
        set.addAll(list);
        return new ArrayList<>(set);
    }

    @Override
    public Integer save(Orders orders) {
        // 1.获取QueryRunner对象
        QueryRunner queryRunner = MyDBUtils.getQueryRunner();
        String sql = "insert into orders(client_ID,commodity_ID,price,finish,count)values(?,?,?,?,?)" ;
        try {
            return queryRunner.update(sql,
                    orders.getClient_id(),
                    orders.getCommodity_id(),
                    orders.getPrice(),
                    orders.getFinish(),
                    orders.getCount());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public Integer deleteByOrders_id(Integer orders_id) {
        QueryRunner queryRunner = MyDBUtils.getQueryRunner();
        String sql = "delete from orders where order_id = ?" ;
        try {
            return queryRunner.update(sql, orders_id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public Orders queryByOrders_id(Integer orders_id) {
        QueryRunner queryRunner = MyDBUtils.getQueryRunner();
        String search_id = "select * from orders where order_id = ?";
        try {
            return queryRunner.query(search_id, new ResultSetHandler<Orders>() {
                @Override
                public Orders handle(ResultSet resultSet) throws SQLException {
                    if (resultSet.next()){
                        Orders orders = new Orders();
                        orders.setOrder_id(orders_id);
                        orders.setClient_id(resultSet.getInt("client_id"));
                        orders.setCommodity_id(resultSet.getInt("Commodity_id"));
                        orders.setPrice(resultSet.getInt("price"));
                        orders.setFinish(resultSet.getString("finish"));
                        orders.setCount(resultSet.getInt("count"));
                        return orders;
                    }
                    return null;
                }
            }, orders_id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Integer updateOrders(Orders orders) {
        QueryRunner queryRunner = MyDBUtils.getQueryRunner();
        String sql = "update orders set client_ID = ?,commodity_ID = ?,price = ?,finish = ?,count = ? where order_id = ?" ;
        try {
            return queryRunner.update(sql,
                    orders.getClient_id(),
                    orders.getCommodity_id(),
                    orders.getPrice(),
                    orders.getFinish(),
                    orders.getCount(),
                    orders.getOrder_id());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
