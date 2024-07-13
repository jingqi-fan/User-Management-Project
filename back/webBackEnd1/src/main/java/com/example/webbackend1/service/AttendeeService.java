package com.example.webbackend1.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.webbackend1.entity.Attendee;
import com.example.webbackend1.entity.AttendeeQuery;
import com.example.webbackend1.entity.AttendeeResult;

import java.util.List;

public interface AttendeeService extends IService<Attendee> {
    /**
     * 添加用户
     * @param attendee
     * @return
     */
    AttendeeResult insertAttendee(Attendee attendee);

    /**
     * 删除单个用户
     * @param id 用户编号
     * @return
     */
    AttendeeResult deleteAttendee(Integer id);

    /**
     * 删除多个用户
     * @param ids 用户编号集合
     * @return
     */
    AttendeeResult deleteAttendeeMore(List<Integer> ids);

    /**
     * 编辑用户
     * @param attendee
     * @return
     */
    AttendeeResult modifyAttendee(Attendee attendee);

    /**
     * 分页查询用户列表
     * @param index 当前页
     * @param size 每页显示数量
     * @param queryParam 筛选条件对象
     * @return
     */
    AttendeeResult findAttendeeList(Integer index, Integer size, AttendeeQuery queryParam);

    /**
     * 查询单个用户详细信息
     * @param id 用户编号
     * @return
     */
    AttendeeResult getAttendeeInfoById(Integer id);

}
