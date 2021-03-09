package com.free.account.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.free.account.model.Account;
import org.apache.ibatis.annotations.Mapper;

/**
 * 账户 mapper接口
 *
 * @author dinghao
 * @date 2021/3/8
 */
@Mapper
public interface AccountMapper extends BaseMapper<Account> {

}
