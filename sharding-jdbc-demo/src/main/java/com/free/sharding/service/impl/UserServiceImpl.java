package com.free.sharding.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.free.sharding.mapper.UserMapper;
import com.free.sharding.model.User;
import com.free.sharding.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户表 service 接口实现
 *
 * @author dinghao
 * @date 2021/2/24
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public List<User> getUsers() {

        return baseMapper.selectList(new QueryWrapper<>());
    }

    @Override
    public boolean addUser(User u) {

        return baseMapper.insert(u) > 0;
    }

    @Override
    public User getUser(Long id) {

        return baseMapper.selectById(id);
    }

    @Override
    public boolean deleteOne(Long id) {

        return baseMapper.deleteById(id) > 0;
    }
}
