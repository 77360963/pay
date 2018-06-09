package com.yunpan.data.dao;

import java.util.List;

import com.yunpan.data.entity.MerchantTradeEntity;

public interface MerchantTradeDao {
    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insert(MerchantTradeEntity record);

    /**
     *
     * @param record
     */
    int insertSelective(MerchantTradeEntity record);

    /**
     * 根据主键获取一条数据库记录
     *
     * @param id
     */
    MerchantTradeEntity selectByPrimaryKey(Long id);

    /**
     *
     * @param record
     */
    int updateByPrimaryKeySelective(MerchantTradeEntity record);

    /**
     * 根据主键来更新数据库记录
     *
     * @param record
     */
    int updateByPrimaryKey(MerchantTradeEntity record);
    
    /**
    *
    * @param record
    */
   int updateMerchantTradeStatus(MerchantTradeEntity record);
   
   /**
    * 查询交易
    * @param userId
    * @return
    */
   List<MerchantTradeEntity> queryTradeByUserId(long userId);
   
   
   /**
    * 查询交易
    * @param userId
    * @return
    */
   List<MerchantTradeEntity> queryTrade(MerchantTradeEntity record);
   
   
   
   
}