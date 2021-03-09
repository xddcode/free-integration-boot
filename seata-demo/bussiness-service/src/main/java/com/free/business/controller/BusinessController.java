package com.free.business.controller;

import com.free.business.service.BusinessService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 总业务测试
 *
 * @author dinghao
 * @date 2021/3/8
 */
@RestController
@AllArgsConstructor
public class BusinessController {

    @Resource
    private BusinessService businessService;

    /**
     * 下单场景测试-正常
     */
    @RequestMapping(path = "/placeOrder")
    public Boolean placeOrder() {
        businessService.placeOrder("U001");
        return true;
    }

    /**
     * 下单场景测试-回滚
     */
    @RequestMapping(path = "/placeOrderFallBack")
    public Boolean placeOrderFallBack() {
        businessService.placeOrder("U002");
        return true;
    }
}
