package com.free.order.service;

import com.free.order.feign.AccountFeignClient;
import com.free.order.mapper.OrderMapper;
import com.free.order.model.Order;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author zlt
 * @date 2019/9/14
 */
@Slf4j
@Service
@AllArgsConstructor
public class OrderService {

    private AccountFeignClient accountFeignClient;

    private OrderMapper orderMapper;

    public void create(String userId, String commodityCode, Integer count) {

        //订单金额
        Integer orderMoney = count * 2;

        Order order = new Order()
                .setUserId(userId)
                .setCommodityCode(commodityCode)
                .setCount(count)
                .setMoney(orderMoney);
        orderMapper.insert(order);

        accountFeignClient.reduce(userId, orderMoney);

    }
}
