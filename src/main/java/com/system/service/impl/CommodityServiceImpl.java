package com.system.service.impl;

import com.system.bean.Commodity;
import com.system.dao.ICommodityDao;
import com.system.dao.impl.CommodityDaoImpl;
import com.system.service.ICommodityService;

import java.util.List;

public class CommodityServiceImpl implements ICommodityService {

    private ICommodityDao CommodityDao = new CommodityDaoImpl();
    @Override
    public List<Commodity> getCommodity(Commodity commodity) {
        return CommodityDao.list(commodity);
    }

    @Override
    public List<Commodity> getCommodityCondition(String str) {
        return CommodityDao.listCondition(str);
    }

    @Override
    public Integer addCommodity(Commodity commodity) {
        return CommodityDao.save(commodity);
    }

    @Override
    public Integer deleteByCommodity_id(Integer Commodity_id) {
        return CommodityDao.deleteByCommodity_id(Commodity_id);
    }

    @Override
    public Commodity queryByCommodity_id(Integer Commodity_id) {
        return CommodityDao.queryByCommodity_id(Commodity_id);
    }

    @Override
    public Integer updateCommodity(Commodity commodity) {
        return CommodityDao.updateCommodity(commodity);
    }

    @Override
    public Integer buyingCommodity(Commodity commodity, Integer Commodity_id, Integer user_id, Integer count) {
        return CommodityDao.buyingCommodity(commodity, Commodity_id, user_id, count);
    }
}
