package com.free.sharding.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.free.sharding.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 用户表 mapper 接口
 *
 * @author dinghao
 * @date 2021/2/24
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    List<User> selectUserList(User u);
}
