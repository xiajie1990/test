package com.atguigu.springcloud.dao;

import com.atguigu.springcloud.entities.PayMent;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ClassName: PayMentDao
 * @Author: xiajie
 * Date: 2020/05/08 18:00:23
 * project name: cloud
 */
@Mapper
public interface PayMentDao {

    public int create(PayMent payment);

    public PayMent getPaymentById(Integer id) ;

}
