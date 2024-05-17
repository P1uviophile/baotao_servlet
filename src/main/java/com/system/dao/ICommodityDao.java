package com.system.dao;

import com.system.bean.Commodity;

import java.util.List;

/**
 *  Commodity的Dao接口
 */
public interface ICommodityDao {

    /*
            查询所有报纸
     */
    public List<Commodity> list(Commodity commodity);

    /*
            条件查询报纸
     */
    public List<Commodity> listCondition(String str);

    /*
           保存报纸信息
     */
    public Integer save(Commodity commodity);

    /**
     * 删除报纸信息
     */
    public Integer deleteByCommodity_id(Integer Commodity_id);

    /**
     * 根据报纸id单条查询报纸信息
     */
    public Commodity queryByCommodity_id(Integer Commodity_id);

    /**
     * 更新报纸信息
     */
    public Integer updateCommodity(Commodity commodity);

    Integer buyingCommodity(Commodity commodity, Integer Commodity_id, Integer user_id, Integer count);
}
