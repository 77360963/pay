package com.yunpan.data.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

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
     
    /**
     * 根据parentUserId 查询
     * @param userId
     * @return
     */
    List<MerchantEntity> queryMerchantByParentUserId(Long userId);
    
    /**
     *根据userId 与 parentUserId 查询
     * @param userId
     * @param parentUserId
     * @return
     */  
    MerchantEntity queryMerchantByUser(@Param("userId") Long userId,@Param("parentUserId") Long parentUserId);
}