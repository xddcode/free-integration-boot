package com.free.sharding.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.free.sharding.model.User;

import java.util.List;

/**
 * 用户表 service 接口
 *
 * @author dinghao
 * @date 2021/2/24
 */
public interface UserService extends IService<User> {

    List<User> getUsers();

    boolean addUser(User u);

    User getUser(Long id);

    boolean deleteOne(Long id);
}
