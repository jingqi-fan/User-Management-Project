package com.example.webbackend1.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class Attendee {
    // 主键ID
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    // 用户名
    private String username;

    // 昵称
    private String nickname;

    // 密码
    private String password;

    // 号码
    private String phone;

    // 性别
    private Character sex;

    // 描述
    @TableField(value = "`describe`") // describe属于数据库关键字，加上``区分
    private String describe;

    // 创建时间
    @TableField(fill = FieldFill.INSERT) // insert操作时自动注入时间
    private Date gmtCreate;
}

