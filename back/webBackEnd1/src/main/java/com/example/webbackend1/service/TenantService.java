package com.example.webbackend1.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.webbackend1.entity.Attendee;
import com.example.webbackend1.entity.AttendeeQuery;
import com.example.webbackend1.entity.AttendeeResult;
import com.example.webbackend1.entity.Tenant;

import java.util.List;

public interface TenantService extends IService<Tenant> {
    /**
     * 添加用户
     * @param tenant
     * @return
     */
    AttendeeResult insertTenant(Tenant tenant);

    /**
     * 删除单个用户
     * @param id 用户编号
     * @return
     */
    AttendeeResult deleteTenant(Integer id);

    /**
     * 删除多个用户
     * @param ids 用户编号集合
     * @return
     */
    AttendeeResult deleteTenantMore(List<Integer> ids);

    /**
     * 编辑用户
     * @param tenant
     * @return
     */
    AttendeeResult modifyTenant(Tenant tenant);

    /**
     * 分页查询用户列表
     * @param index 当前页
     * @param size 每页显示数量
     * @param queryParam 筛选条件对象
     * @return
     */
    AttendeeResult findTenantList(Integer index, Integer size, AttendeeQuery queryParam);

    /**
     * 查询单个用户详细信息
     * @param id 用户编号
     * @return
     */
    AttendeeResult getTenantInfoById(Integer id);

}
