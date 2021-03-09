package com.free.order.controller;

import com.free.order.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 订单的控制层
 *
 * @author dinghao
 * @date 2021/3/8
 */
@RestController
@RequestMapping("order")
@AllArgsConstructor
public class OrderController {

    private OrderService orderService;

    /**
     * 创建订单
     *
     * @param userId        用户id
     * @param commodityCode 订单编号
     * @param count         数量
     */
    @RequestMapping("/create")
    public boolean create(String userId, String commodityCode, Integer count) {
        orderService.create(userId, commodityCode, count);
        return true;
    }
}
