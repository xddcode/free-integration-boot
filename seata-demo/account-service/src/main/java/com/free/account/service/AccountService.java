package com.free.account.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.free.account.mapper.AccountMapper;
import com.free.account.model.Account;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 账户表业务接口
 *
 * @author dinghao
 * @date 2021/3/8
 */
@Slf4j
@Service
@AllArgsConstructor
public class AccountService {

    private AccountMapper accountMapper;

    /**
     * 减账号金额
     */
    public void reduce(String userId, int money) {

        if ("U002".equals(userId)) {
            throw new RuntimeException("this is a mock Exception");
        }

        QueryWrapper<Account> wrapper = new QueryWrapper<>();
        wrapper.setEntity(new Account().setUserId(userId));
        Account account = accountMapper.selectOne(wrapper);
        account.setMoney(account.getMoney() - money);
        accountMapper.updateById(account);
    }
}
