package com.free.inventory.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 库存表实体
 *
 * @author dinghao
 * @date 2021/3/8
 */
@Data
@Accessors(chain = true)
@TableName("free_seata_inventory")
@EqualsAndHashCode(callSuper = true)
public class Inventory extends Model<Inventory> {

    @TableId
    private Long id;

    private String commodityCode;

    private Long count;
}
