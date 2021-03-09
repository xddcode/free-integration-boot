package com.free.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.free.order.model.Order;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单表 mapper 接口
 *
 * @author dinghao
 * @date 2021/3/8
 */
@Mapper
public interface OrderMapper extends BaseMapper<Order> {

}
