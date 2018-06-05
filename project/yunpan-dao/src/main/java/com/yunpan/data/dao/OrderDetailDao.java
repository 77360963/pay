package com.yunpan.data.dao;

import com.yunpan.data.entity.OrderDetailEntity;

public interface OrderDetailDao {
    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insert(OrderDetailEntity record);

    /**
     *
     * @param record
     */
    int insertSelective(OrderDetailEntity record);

    /**
     * 根据主键获取一条数据库记录
     *
     * @param id
     */
    OrderDetailEntity selectByPrimaryKey(Long id);

    /**
     *
     * @param record
     */
    int updateByPrimaryKeySelective(OrderDetailEntity record);

    /**
     * 根据主键来更新数据库记录
     *
     * @param record
     */
    int updateByPrimaryKey(OrderDetailEntity record);
}