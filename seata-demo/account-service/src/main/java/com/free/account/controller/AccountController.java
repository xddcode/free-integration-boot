package com.free.account.controller;

import com.free.account.service.AccountService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 账户表控制层
 *
 * @author dinghao
 * @date 2021/3/8
 */
@Slf4j
@RestController
@AllArgsConstructor
public class AccountController {

    private AccountService accountService;

    /**
     * 账号扣钱
     */
    @PostMapping(value = "/account/reduce")
    public boolean reduce(String userId, Integer money) {
        accountService.reduce(userId, money);
        return true;
    }
}
