package com.free.inventory.controller;

import com.free.inventory.service.InventoryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 库存表控制层
 *
 * @author dinghao
 * @date 2021/3/8
 */
@RestController
@RequestMapping("inventory")
@AllArgsConstructor
public class InventoryController {

    private InventoryService inventoryService;

    /**
     * 减库存
     *
     * @param commodityCode 商品代码
     * @param count         数量
     */
    @RequestMapping(path = "/deduct")
    public boolean deduct(String commodityCode, Integer count) {
        inventoryService.deduct(commodityCode, count);
        return true;
    }
}
