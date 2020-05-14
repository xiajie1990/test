package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.FeignService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName: FeignController
 * @Author: xiajie
 * Date: 2020/05/10 18:35:46
 * project name: cloud
 */
@RestController
@Slf4j
@DefaultProperties(defaultFallback = "paymentTimeOutFallBackDefult")
public class FeignController {

    @Resource
    private FeignService feignService;

    @GetMapping("/consumer/payment/hystrix/{id}")
    //@HystrixCommand
    public String payMentInfo(@PathVariable("id") Integer id){
        return feignService.paymentInfo_Ok(id);
    }

    @GetMapping("/consumer/payment/hystrix/error/{id}")
    //@HystrixCommand
    public String payMentInfoError(@PathVariable("id") Integer id) {
        return feignService.paymentInfo_Error(id);
    }

    public String paymentTimeOutFallBackDefult(){
        return "我是消费者80，对方支付系统繁忙，请稍后再试";
    }
}
