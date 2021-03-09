package com.free.inventory.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.free.inventory.mapper.InventoryMapper;
import com.free.inventory.model.Inventory;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 库存表业务接口
 *
 * @author dinghao
 * @date 2021/3/8
 */
@Slf4j
@Service
@AllArgsConstructor
public class InventoryService {

    private InventoryMapper inventoryMapper;

    /**
     * 减库存
     *
     * @param commodityCode 商品编号
     * @param count         数量
     */
    public void deduct(String commodityCode, int count) {
        QueryWrapper<Inventory> wrapper = new QueryWrapper<>();
        wrapper.setEntity(new Inventory().setCommodityCode(commodityCode));
        Inventory storage = inventoryMapper.selectOne(wrapper);
        storage.setCount(storage.getCount() - count);
        inventoryMapper.updateById(storage);
    }
}
