package com.yunpan.data.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

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
    * 查询指定商户的充值记录
    * @param merchantId
    * @return
    */
   List<MerchantTradeEntity> queryMerchantTradeByUserId(@Param("userId") Long userId);
}