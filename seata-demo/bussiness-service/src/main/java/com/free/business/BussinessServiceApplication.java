package com.free.business;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 总业务
 *
 * @author zlt
 * @date 2019/9/14
 */
@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
public class BussinessServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BussinessServiceApplication.class, args);
    }

}
