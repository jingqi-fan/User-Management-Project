package com.example.webbackend1.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    /**
     * 新增数据执行
     *
     * insert插入数据到数据库操作时执行
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        // 配置初始创建时间
        this.setFieldValByName("gmtCreate", new Date(), metaObject);
    }

    /**
     * 修改数据执行
     *
     * update修改数据库数据操作时执行
     */
    @Override
    public void updateFill(MetaObject metaObject) {
    }
}

