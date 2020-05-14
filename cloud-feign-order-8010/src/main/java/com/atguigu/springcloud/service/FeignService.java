package com.atguigu.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @ClassName: FeignService
 * @Author: xiajie
 * Date: 2020/05/10 18:35:59
 * project name: cloud
 */
@Component
@FeignClient(value = "CLOUD-HYSTRIX-PAYMENT",fallback = FeignServiceImpl.class)
public interface FeignService {

    @GetMapping("/consumer/payment/hystrix/error/{id}")
    public String paymentInfo_Error(@PathVariable("id") Integer id);

    @GetMapping("/consumer/payment/hystrix/{id}")
    public String paymentInfo_Ok(@PathVariable("id") Integer id);
}

