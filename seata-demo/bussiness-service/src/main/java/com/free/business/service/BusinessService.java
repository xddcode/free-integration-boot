package com.free.business.service;

import com.free.business.feign.OrderFeignClient;
import com.free.business.feign.InventoryFeignClient;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 业务
 *
 * @author zlt
 * @date 2019/9/14
 */
@Slf4j
@Service
@AllArgsConstructor
public class BusinessService {

    private static final String COMMODITY_CODE = "P001";

    private static final int ORDER_COUNT = 1;

    private OrderFeignClient orderFeignClient;

    private InventoryFeignClient inventoryFeignClient;

    /**
     * 下订单
     */
    @GlobalTransactional
    public void placeOrder(String userId) {
        inventoryFeignClient.deduct(COMMODITY_CODE, ORDER_COUNT);

        orderFeignClient.create(userId, COMMODITY_CODE, ORDER_COUNT);
    }
}
