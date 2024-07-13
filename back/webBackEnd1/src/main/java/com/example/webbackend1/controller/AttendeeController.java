package com.example.webbackend1.controller;

import com.example.webbackend1.entity.Attendee;
import com.example.webbackend1.entity.AttendeeQuery;
import com.example.webbackend1.entity.AttendeeResult;
import com.example.webbackend1.entity.Result;
import com.example.webbackend1.service.AttendeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/attendee")
@CrossOrigin
public class AttendeeController {

    @Autowired
    private AttendeeService attendeeService;

    // 添加用户
    @PostMapping("")
    public AttendeeResult insertAttendee(@RequestBody Attendee attendee) {
        return attendeeService.insertAttendee(attendee);
    }

    // 删除单个用户
    @DeleteMapping("{id}")
    public AttendeeResult deleteAttendee(@PathVariable(value = "id") Integer id) {
        return attendeeService.deleteAttendee(id);
    }

    // 删除多个用户
    @DeleteMapping("")
    public AttendeeResult deleteAttendeeMore(@RequestBody List<Integer> ids) {
        return attendeeService.deleteAttendeeMore(ids);
    }

    // 编辑用户
    @PutMapping("")
    public AttendeeResult modifyAttendee(@RequestBody Attendee attendee) {
        return attendeeService.modifyAttendee(attendee);
    }

    // 查询用户列表
    @PostMapping("{index}/{size}")
    public AttendeeResult findAttendeeList(@PathVariable(value = "index") Integer index,
                                       @PathVariable(value = "size") Integer size,
                                       @RequestBody(required = false) AttendeeQuery attendeeQuery) {
        return attendeeService.findAttendeeList(index, size, attendeeQuery);
    }

    // 根据用户编号查询用户信息
    @PostMapping("{id}")
    public AttendeeResult getAttendeeInfo(@PathVariable(value = "id") Integer id) {
        return attendeeService.getAttendeeInfoById(id);
    }



}

