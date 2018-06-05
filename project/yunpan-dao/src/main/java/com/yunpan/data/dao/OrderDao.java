package com.yunpan.data.dao;

import com.yunpan.data.entity.OrderEntity;

public interface OrderDao {
    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insert(OrderEntity record);

    /**
     *
     * @param record
     */
    int insertSelective(OrderEntity record);

    /**
     * 根据主键获取一条数据库记录
     *
     * @param id
     */
    OrderEntity selectByPrimaryKey(Long id);

    /**
     *
     * @param record
     */
    int updateByPrimaryKeySelective(OrderEntity record);

    /**
     * 根据主键来更新数据库记录
     *
     * @param record
     */
    int updateByPrimaryKey(OrderEntity record);
}