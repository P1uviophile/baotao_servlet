package com.system.service;

import com.system.bean.Orders;

import java.util.List;

public interface IOrdersService {
    public List<Orders> getOrders(Orders order);

    public List<Orders> getOrdersCondition(String str);

    public Integer addOrders(Orders order);

    public Integer deleteByOrders_id(Integer order_id);

    public Orders queryByOrders_id(Integer order_id);

    public Integer updateOrders(Orders order);
}
