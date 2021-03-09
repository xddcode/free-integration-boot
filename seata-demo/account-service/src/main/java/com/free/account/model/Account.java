package com.free.account.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 帐户表实体
 *
 * @author dinghao
 * @date 2021/3/8
 */
@Data
@Accessors(chain = true)
@TableName("free_seata_account")
@EqualsAndHashCode(callSuper = true)
public class Account extends Model<Account> {

    @TableId
    private Long id;

    private String userId;

    private Integer money;
}
