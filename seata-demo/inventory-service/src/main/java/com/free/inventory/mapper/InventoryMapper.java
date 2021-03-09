package com.free.inventory.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.free.inventory.model.Inventory;
import org.apache.ibatis.annotations.Mapper;

/**
 * 库存表 mapper接口
 *
 * @author dinghao
 * @date 2021/3/8
 */
@Mapper
public interface InventoryMapper extends BaseMapper<Inventory> {
}
