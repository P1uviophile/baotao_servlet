package com.system.service;

import com.system.bean.Commodity;

import java.util.List;

public interface ICommodityService {
    public List<Commodity> getCommodity(Commodity commodity);

    public List<Commodity> getCommodityCondition(String str);

    public Integer addCommodity(Commodity commodity);

    public Integer deleteByCommodity_id(Integer Commodity_id);

    public Commodity queryByCommodity_id(Integer Commodity_id);

    public Integer updateCommodity(Commodity commodity);

    public Integer buyingCommodity(Commodity commodity, Integer Commodity_id, Integer user_id, Integer count);
}
