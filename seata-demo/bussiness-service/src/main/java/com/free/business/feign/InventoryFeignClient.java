package com.free.business.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 库存
 *
 * @author dinghao
 * @date 2021/3/8
 */
@FeignClient(name = "inventory-service")
public interface InventoryFeignClient {

    @GetMapping("inventory/deduct")
    Boolean deduct(@RequestParam("commodityCode") String commodityCode, @RequestParam("count") Integer count);
}
