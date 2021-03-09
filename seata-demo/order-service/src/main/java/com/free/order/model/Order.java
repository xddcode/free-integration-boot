package com.free.order.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 订单表实体
 *
 * @author dinghao
 * @date 2021/3/8
 */

@Data
@Accessors(chain = true)
@TableName("free_seata_order")
@EqualsAndHashCode(callSuper = true)
public class Order extends Model<Order> {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String userId;

    private String commodityCode;

    private Integer count;

    private Integer money;
}
