package com.yunpan.data.dao;

import com.yunpan.data.entity.MerchantRateEntity;

public interface MerchantRateDao {
    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insert(MerchantRateEntity record);

    /**
     *
     * @param record
     */
    int insertSelective(MerchantRateEntity record);

    /**
     * 根据主键获取一条数据库记录
     *
     * @param id
     */
    MerchantRateEntity selectByPrimaryKey(Long id);

    /**
     *
     * @param record
     */
    int updateByPrimaryKeySelective(MerchantRateEntity record);

    /**
     * 根据主键来更新数据库记录
     *
     * @param record
     */
    int updateByPrimaryKey(MerchantRateEntity record);
    
    
    /**
     * 根据主键获取一条数据库记录
     *
     * @param id
     */
    MerchantRateEntity selectByUserId(Long userId);   
  
}