package com.yunpan.data.dao;

import java.util.List;

import com.yunpan.data.entity.MerchantRechargeEntity;

public interface MerchantRechargeDao {
    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insert(MerchantRechargeEntity record);

    /**
     *
     * @param record
     */
    int insertSelective(MerchantRechargeEntity record);

    /**
     * 根据主键获取一条数据库记录
     *
     * @param id
     */
    MerchantRechargeEntity selectByPrimaryKey(Long id);

    /**
     *
     * @param record
     */
    int updateByPrimaryKeySelective(MerchantRechargeEntity record);

    /**
     * 根据主键来更新数据库记录
     *
     * @param record
     */
    int updateByPrimaryKey(MerchantRechargeEntity record);
    
    /**
    *
    * @param record
    */
   int updateMerchantRechargeStatus(MerchantRechargeEntity record);
   
   
   /**
    * 查询指定商户的充值记录
    * @param merchantId
    * @return
    */
   List<MerchantRechargeEntity> queryMerchantRechargeByMerchantId(Long merchantId);
    
    
}