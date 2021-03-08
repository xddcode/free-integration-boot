package com.free.sharding.controller;

import com.free.common.utils.R;
import com.free.sharding.model.User;
import com.free.sharding.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户管理
 *
 * @author dinghao
 * @date 2021/2/24
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public R getUsers() {
        List<User> users = userService.getUsers();
        return R.succeed(users, "查询成功");
    }

    @GetMapping("/{id}")
    public R getUser(@PathVariable Long id) {
        User u = userService.getUser(id);
        return R.succeed(u, "查询成功");
    }

    @PostMapping("/addUsers")
    public R add() {
        for (int i = 1; i <= 5; i++) {
            User user = new User();
            user.setName("测试name-" + i);
            user.setPhone("phone-" + (i));
            user.setPassword("pw" + i);
            userService.addUser(user);
        }
        return R.succeed("添加成功");
    }

    @DeleteMapping("/{id}")
    public R delete(@PathVariable Long id) {
        if (userService.deleteOne(id)) {
            return R.succeed("删除成功");
        }
        return R.succeed("删除失败");
    }
}
