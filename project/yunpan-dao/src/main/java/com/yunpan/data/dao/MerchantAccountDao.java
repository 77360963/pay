package com.yunpan.data.dao;





import org.apache.ibatis.annotations.Param;

import com.yunpan.data.entity.MerchantAccountEntity;

public interface MerchantAccountDao {
    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insert(MerchantAccountEntity record);

    /**
     *
     * @param record
     */
    int insertSelective(MerchantAccountEntity record);

    /**
     * 根据主键获取一条数据库记录
     *
     * @param id
     */
    MerchantAccountEntity selectByPrimaryKey(Long id);

    /**
     *
     * @param record
     */
    int updateByPrimaryKeySelective(MerchantAccountEntity record);

    /**
     * 根据主键来更新数据库记录
     *
     * @param record
     */
    int updateByPrimaryKey(MerchantAccountEntity record);
    
    /**
     * 根据merchanId查找
     * @param record
     * @return
     */
    MerchantAccountEntity selectByUserId(Long userId);
    
    /**
     * 
     * @param memberId
     * @param rechargeAmount
     * @return
     */
    int merchantRecharge(@Param("userId") Long userId,@Param("rechargeAmount") int rechargeAmount);
    
    /**
     * 商户提现
     * @param merchantId
     * @param withdrawAmount
     * @return
     */
    int merchantWithdraw(@Param("userId") Long userId,@Param("withdrawAmount") int withdrawAmount);
    
    /**
     * 商户打款
     * @param merchantId
     * @param withdrawAmount
     * @return
     */
    int merchantWithdrawConfirm(@Param("userId") Long userId,@Param("withdrawAmount") int withdrawAmount);
}