package com.free.sharding.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 用户表实体类
 *
 * @author dinghao
 * @date 2021/2/24
 */
@Data
@TableName("t_user")
@EqualsAndHashCode(callSuper = true)
public class User extends Model<User> {

    private Long id;

    private String name;

    private Integer cityId;

    private Integer sex;

    private String phone;

    private String email;

    private Date createTime;

    private String password;
}
