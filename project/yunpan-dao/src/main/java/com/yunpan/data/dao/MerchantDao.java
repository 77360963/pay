package com.yunpan.data.dao;

import java.util.List;

import com.yunpan.data.entity.MerchantEntity;

public interface MerchantDao {
    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insert(MerchantEntity record);

    /**
     *
     * @param record
     */
    int insertSelective(MerchantEntity record);

    /**
     * 根据主键获取一条数据库记录
     *
     * @param id
     */
    MerchantEntity selectByPrimaryKey(Long id);

    /**
     *
     * @param record
     */
    int updateByPrimaryKeySelective(MerchantEntity record);

    /**
     * 根据主键来更新数据库记录
     *
     * @param record
     */
    int updateByPrimaryKey(MerchantEntity record);
    
    
    MerchantEntity selectMerchantEntityByUserId(Long userId);
    
    
    List<MerchantEntity> queryMerchant();
}