package com.system.dao.impl;

import com.system.bean.Commodity;
import com.system.dao.ICommodityDao;
import com.system.utils.MyDBUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class CommodityDaoImpl implements ICommodityDao {

    @Override
    public List<Commodity> list(Commodity commodity) {
        // 通过DBUtils来实现数据库的操作
        QueryRunner queryRunner = MyDBUtils.getQueryRunner();
        // 构建SQL语句
        String test = "select * from commodity";
        List<Commodity> list = null;
        try {
            list = queryRunner.query(test, new BeanListHandler<Commodity>(Commodity.class));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    @Override
    public List<Commodity> listCondition(String str) {
        // 1.获取QueryRunner对象
        QueryRunner queryRunner = MyDBUtils.getQueryRunner();
        String search_id = "select * from commodity where commodity_id like ?";
        String search_name = "select * from commodity where commodity_name like ?";
        List<Commodity> list = null;
        try {
            // 添加通配符 % 到参数值中
            String searchTerm = "%" + str + "%";
            list = queryRunner.query(search_id, new BeanListHandler<Commodity>(Commodity.class), searchTerm);
            list.addAll(queryRunner.query(search_name, new BeanListHandler<Commodity>(Commodity.class), searchTerm));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Set<Commodity> set = new TreeSet<>(Comparator.comparing(Commodity::getCommodity_id));
        set.addAll(list);
        return new ArrayList<>(set);
    }

    @Override
    public Integer save(Commodity commodity) {
        // 1.获取QueryRunner对象
        QueryRunner queryRunner = MyDBUtils.getQueryRunner();
        String sql = "insert into commodity(commodity_id,commodity_name,price,introduce)values(?,?,?,?)" ;
        try {
            return queryRunner.update(sql,
                    commodity.getCommodity_id(),
                    commodity.getCommodity_name(),
                    commodity.getPrice(),
                    commodity.getIntroduce());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public Integer deleteByCommodity_id(Integer commodity_id) {
        QueryRunner queryRunner = MyDBUtils.getQueryRunner();
        String sql = "delete from commodity where commodity_id = ?" ;
        try {
            return queryRunner.update(sql, commodity_id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public Commodity queryByCommodity_id(Integer commodity_id) {
        QueryRunner queryRunner = MyDBUtils.getQueryRunner();
        String search_id = "select * from commodity where commodity_id = ?";
        try {
            return queryRunner.query(search_id, new ResultSetHandler<Commodity>() {
                @Override
                public Commodity handle(ResultSet resultSet) throws SQLException {
                    if (resultSet.next()){
                        Commodity commodity = new Commodity();
                        commodity.setCommodity_id(commodity_id);
                        commodity.setCommodity_name(resultSet.getString("commodity_name"));
                        commodity.setPrice(resultSet.getInt("price"));
                        commodity.setIntroduce(resultSet.getString("introduce"));
                        return commodity;
                    }
                    return null;
                }
            }, commodity_id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Integer updateCommodity(Commodity commodity) {
        QueryRunner queryRunner = MyDBUtils.getQueryRunner();
        String sql = "update commodity set commodity_name = ?, price = ?, introduce = ? where commodity_id = ?" ;
        try {
            return queryRunner.update(sql,
                    commodity.getCommodity_name(),
                    commodity.getPrice(),
                    commodity.getIntroduce(),
                    commodity.getCommodity_id());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public Integer buyingCommodity(Commodity commodity, Integer Commodity_id, Integer user_id, Integer count) {
        // 1.获取QueryRunner对象
        QueryRunner queryRunner = MyDBUtils.getQueryRunner();
        String sql = "insert into orders(client_ID,Commodity_ID,price,finish,count)values(?,?,?,?,?)" ;
        try {
            return queryRunner.update(sql,
                    user_id,
                    Commodity_id,
                    commodity.getPrice()*count,
                    0,
                    count);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
