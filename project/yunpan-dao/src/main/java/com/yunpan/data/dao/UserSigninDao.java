package com.yunpan.data.dao;

import org.apache.ibatis.annotations.Param;

import com.yunpan.data.entity.UserSigninEntity;

public interface UserSigninDao {
    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insert(UserSigninEntity record);

    /**
     *
     * @param record
     */
    int insertSelective(UserSigninEntity record);

    /**
     * 根据主键获取一条数据库记录
     *
     * @param id
     */
    UserSigninEntity selectByPrimaryKey(Long id);

    /**
     *
     * @param record
     */
    int updateByPrimaryKeySelective(UserSigninEntity record);

    /**
     * 根据主键来更新数据库记录
     *
     * @param record
     */
    int updateByPrimaryKey(UserSigninEntity record);
    
   /**
    *  根据用户ID和日期查询
    * @param userId
    * @param signinDate
    * @return  
    */
    UserSigninEntity selectByUserIdandDate(@Param("userId") long userId,@Param("signinDate") String signinDate);
}