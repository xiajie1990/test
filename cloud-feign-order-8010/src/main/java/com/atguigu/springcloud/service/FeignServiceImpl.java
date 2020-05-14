package com.atguigu.springcloud.service;

import org.springframework.stereotype.Component;

/**
 * @ClassName: FeignServiceImpl
 * @Author: xiajie
 * Date: 2020/05/10 23:51:05
 * project name: cloud
 */
@Component
public class FeignServiceImpl implements FeignService{

    @Override
    public String paymentInfo_Error(Integer id) {
        return "------this method is error---------";
    }

    @Override
    public String paymentInfo_Ok(Integer id) {
        return "------this method is ok---------";
    }
}
