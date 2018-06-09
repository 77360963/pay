package com.yunpan.data.dao;

import org.apache.ibatis.annotations.Param;

import com.yunpan.data.entity.UniUserEntity;

public interface UniUserDao {
    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insert(UniUserEntity record);

    /**
     *
     * @param record
     */
    int insertSelective(UniUserEntity record);

    /**
     * 根据主键获取一条数据库记录
     *
     * @param id
     */
    UniUserEntity selectByPrimaryKey(Long id);

    /**
     *
     * @param record
     */
    int updateByPrimaryKeySelective(UniUserEntity record);

    /**
     * 根据主键来更新数据库记录
     *
     * @param record
     */
    int updateByPrimaryKey(UniUserEntity record);
    
    /**
     * 根据用户和密码登录
     * @return
     */
    UniUserEntity selectByLoginPassword(@Param("loginName") String loginName,@Param("password") String password);
    
    /**
     * 根据用户和密码登录
     * @return
     */
    UniUserEntity selectByLoginName(@Param("loginName") String loginName);
}