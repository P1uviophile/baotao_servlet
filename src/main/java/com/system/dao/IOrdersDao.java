package com.system.dao;

import com.system.bean.Orders;

import java.util.List;

/**
 *  orders的Dao接口
 */
public interface IOrdersDao {

    /*
            查询所有报纸
     */
    public List<Orders> list(Orders orders);

    /*
            条件查询报纸
     */
    public List<Orders> listCondition(String str);

    /*
           保存报纸信息
     */
    public Integer save(Orders orders);

    /**
     * 删除报纸信息
     */
    public Integer deleteByOrders_id(Integer orders_id);

    /**
     * 根据报纸id单条查询报纸信息
     */
    public Orders queryByOrders_id(Integer orders_id);

    /**
     * 更新报纸信息
     */
    public Integer updateOrders(Orders orders);
}
