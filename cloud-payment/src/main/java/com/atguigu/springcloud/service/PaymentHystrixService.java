package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entities.PayMent;
import org.apache.ibatis.annotations.Param;

/**
 * @author wsk
 * @date 2020/3/14 10:21
 */
public interface PaymentHystrixService {

    public int create(PayMent payment);

    public PayMent getPaymentById(@Param("id") Integer id);

    String paymentCircuitBreaker(Integer id);

    String paymentInfo_Error(Integer id);
}