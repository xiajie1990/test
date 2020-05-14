package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.PayMent;
import com.atguigu.springcloud.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName: PayMentController
 * @Author: xiajie
 * Date: 2020/05/08 15:01:00
 * project name: cloud
 */
@RestController
@Slf4j
@DefaultProperties(defaultFallback = "payment_Global_FallbackMethod")
public class PayMentController {

    @Resource
    private PaymentHystrixService payMentService;
    @Value("${server.port}")
    private String serverPort;
    @Resource
    private DiscoveryClient discoveryClient;
    @Resource
    private PaymentHystrixService paymentHystrixService;

    /**
     * 新增
     * @param payMent
     * @return
     */
    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody PayMent payMent){
        int result = payMentService.create(payMent);
        log.info("***新增,端口号:"+serverPort,result);
        return new CommonResult(200,"新增成功",result);
    }

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @GetMapping(value = "/payment/hystrix/{id}")
    public CommonResult<PayMent> getPaymentById(@PathVariable("id") Integer id){
        PayMent payment = payMentService.getPaymentById(id);
        log.info("*****插入结果******："+payment);
        if(payment != null){
            return new CommonResult(200,"查询成功,端口号："+serverPort,payment);
        }else{
            return new CommonResult(444,"没有对应记录,查询ID："+id,null);
        }
    }

    @GetMapping("/payment/hystrix/error/{id}")
    /*@HystrixCommand(fallbackMethod = "paymentTimeOutFallBackMethod",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "1500")
    })*/
    @HystrixCommand
    public String paymentInfo_Error(@PathVariable("id") Integer id){
        int age = 10/0;
        return paymentHystrixService.paymentInfo_Error(id);
    }

    //服务熔断
    @GetMapping("/payment/circuit/{id}")
    public String paymentCircuitBreaker(@PathVariable("id") Integer id){
        String result = payMentService.paymentCircuitBreaker(id);
        log.info("****result："+result);
        return result;
    }

    @GetMapping(value = "/payment/discovery")
    public Object getDiscoveryClient(){
        //注册中心所有的服务名称
        List<String> discoverylist = discoveryClient.getServices();
        for (String element:discoverylist) {
            log.info("服务注册名称："+element);
        }

        //具体的服务实例
        List<ServiceInstance> instances = discoveryClient.getInstances("cloud-payment");
        for (ServiceInstance service:instances) {
            log.info(service.getHost()+"\t"+service.getPort()+"\t"+service.getUri());
        }
        return this.discoveryClient;
    }

    /**
     * 独有forback
     * @param id
     * @return
     */
    public String paymentTimeOutFallBackMethod(@PathVariable("id") Integer id){
        return "我是消费者80，对方支付系统繁忙，请稍后再试，o(╥﹏╥)o";
    }

    /**
     * 全局fallback
     * @return
     */
    public String payment_Global_FallbackMethod(){
        return "Global异常处理信息，请稍后再试。/(╥﹏╥)/~~";
    }
}
