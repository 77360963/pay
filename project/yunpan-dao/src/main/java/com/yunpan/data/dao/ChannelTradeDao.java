package com.yunpan.data.dao;

import com.yunpan.data.entity.ChannelTradeEntity;

public interface ChannelTradeDao {
    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insert(ChannelTradeEntity record);

    /**
     *
     * @param record
     */
    int insertSelective(ChannelTradeEntity record);

    /**
     * 根据主键获取一条数据库记录
     *
     * @param id
     */
    ChannelTradeEntity selectByPrimaryKey(Long id);

    /**
     *
     * @param record
     */
    int updateByPrimaryKeySelective(ChannelTradeEntity record);

    /**
     * 根据主键来更新数据库记录
     *
     * @param record
     */
    int updateByPrimaryKey(ChannelTradeEntity record);
    
    
    /**
     * 根据主键获取一条数据库记录
     *
     * @param id
     */
    ChannelTradeEntity selectByRequestTradeNo(String requestTradeNo);
}