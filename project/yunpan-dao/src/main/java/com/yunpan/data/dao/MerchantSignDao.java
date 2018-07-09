package com.yunpan.data.dao;

import com.yunpan.data.entity.MerchantSignEntity;

public interface MerchantSignDao {
    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insert(MerchantSignEntity record);

    /**
     *
     * @param record
     */
    int insertSelective(MerchantSignEntity record);

    /**
     * 根据主键获取一条数据库记录
     *
     * @param id
     */
    MerchantSignEntity selectByPrimaryKey(Long id);

    /**
     *
     * @param record
     */
    int updateByPrimaryKeySelective(MerchantSignEntity record);

    /**
     * 根据主键来更新数据库记录
     *
     * @param record
     */
    int updateByPrimaryKey(MerchantSignEntity record);
    
    /**
     * 根据userId查询
     * @param userId
     * @return
     */
    MerchantSignEntity selectByUserId(Long userId);
}