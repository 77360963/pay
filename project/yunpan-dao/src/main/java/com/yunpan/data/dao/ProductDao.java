package com.yunpan.data.dao;

import com.yunpan.data.entity.ProductEntity;

public interface ProductDao {
    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insert(ProductEntity record);

    /**
     *
     * @param record
     */
    int insertSelective(ProductEntity record);

    /**
     * 根据主键获取一条数据库记录
     *
     * @param id
     */
    ProductEntity selectByPrimaryKey(Long id);

    /**
     *
     * @param record
     */
    int updateByPrimaryKeySelective(ProductEntity record);

    /**
     * 根据主键来更新数据库记录
     *
     * @param record
     */
    int updateByPrimaryKey(ProductEntity record);
}