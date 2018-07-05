package com.yunpan.service.service;

import java.math.BigDecimal;
import java.util.List;

import com.yunpan.service.service.bean.MerchantInfoBean;

public interface UserService {
     /**
      * 用户签到
      * @param userId
      * @return
      */
     public boolean userSignin(long userId);
     
     
     /**
      * 查询用户下级用户
      * @param userId
      * @return
      */
     public List<MerchantInfoBean> queryRecommendTradeList(long userId);
     
     /**
      * 修改下级用户费率
      * @param userId
      * @param parentId
      * @param rate
      * @return
      */
     public int modfiyRecommendRate(long userId,long parentUserId,BigDecimal rate);
}
