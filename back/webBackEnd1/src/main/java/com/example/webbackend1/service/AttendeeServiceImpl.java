package com.example.webbackend1.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.webbackend1.entity.Attendee;
import com.example.webbackend1.entity.AttendeeQuery;
import com.example.webbackend1.entity.AttendeeResult;
import com.example.webbackend1.mapper.AttendeeMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

@Service
@Transactional
public class AttendeeServiceImpl extends ServiceImpl<AttendeeMapper, Attendee> implements AttendeeService{

    @Autowired
    private AttendeeMapper baseMapper;

    @Override
    public AttendeeResult insertAttendee(Attendee attendee) {
        if (attendee == null) return AttendeeResult.error().message("参数错误");

        // 用户名
        String username = attendee.getUsername();

        // 构建条件对象, 查询是否已经存在用户名
        QueryWrapper<Attendee> wrapper = new QueryWrapper<>();
        wrapper.select("id");
        wrapper.eq("username", username);
        wrapper.last("limit 1");

        // 查询判断, 如果查询出来有数据, 则不为null
        if (this.baseMapper.selectOne(wrapper) != null) AttendeeResult.error().message("该用户名已存在");

        // 执行插入数据操作
        return this.baseMapper.insert(attendee) == 0 ? AttendeeResult.error().message("添加用户失败") : AttendeeResult.ok();
    }
    @Override
    public AttendeeResult deleteAttendee(Integer id) {
        if (id == null || id <= 0) return AttendeeResult.error().message("参数错误");

        return  this.baseMapper.deleteById(id) == 0 ? AttendeeResult.error().message("删除失败") : AttendeeResult.ok();
    }
    @Override
    public AttendeeResult deleteAttendeeMore(List<Integer> ids) {
        if (ids.size() == 0) return AttendeeResult.error().message("参数错误");

        return this.baseMapper.deleteBatchIds(ids) != ids.size() ? AttendeeResult.error().message("删除失败") : AttendeeResult.ok();
    }
    @Override
    public AttendeeResult modifyAttendee(Attendee attendee) {
        if (attendee == null || attendee.getId() == null || attendee.getId() <= 0) return AttendeeResult.error().message("参数错误");

        return this.baseMapper.updateById(attendee) == 0 ? AttendeeResult.error().message("编辑用户失败") : AttendeeResult.ok();
    }
    @Override
    public AttendeeResult getAttendeeInfoById(Integer id) {
        if (id == null || id <= 0) return AttendeeResult.error().message("参数错误");

        return AttendeeResult.ok().data("userInfo", this.baseMapper.selectById(id));
    }
    @Override
    public AttendeeResult findAttendeeList(Integer index, Integer size, AttendeeQuery attendeeQuery) {
        if (index == null || size == null || index <= 0 || size <= 0) {
            return AttendeeResult.error().message("参数错误");
        }else if (size > 10) {
            return AttendeeResult.error().message("一次最多10条数据");
        }

        // 构建分页对象
        Page<Attendee> page = new Page<>(index, size);
        // 查询
        IPage<Attendee> iPage = this.baseMapper.findAttendeeList(page, attendeeQuery);

        // 回传两个数据, 一个 userList --> 用户数据列表, 一个 total -> 总页数
        return AttendeeResult.ok().data("userList", iPage.getRecords()).data("total", iPage.getTotal());
    }


}
