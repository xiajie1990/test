package com.atguigu.springcloud;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer//指定当前工程为euraka注册中心
public class EurekaMain7004 {
    public static void main(String[] args) {
        SpringApplication.run(EurekaMain7004.class,args);
    }
}