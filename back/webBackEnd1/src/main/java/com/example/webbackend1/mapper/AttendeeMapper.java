package com.example.webbackend1.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.webbackend1.entity.Attendee;
import com.example.webbackend1.entity.AttendeeQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AttendeeMapper extends BaseMapper<Attendee> {

    /**
     * 查询用户列表（带多条件），使用注解来定义 SQL
     * @param page 分页对象
     * @param attendeeQuery 筛选条件
     * @return 分页查询结果
     */
    @Select("<script>" +
            "select * from attendee " +
            "<where>" +
            "   1=1 " +
            "   <if test='attendeeQuery.username != null and attendeeQuery.username.trim() != \"\"'> " +
            "       and username = #{attendeeQuery.username} " +
            "   </if> " +
            "   <if test='attendeeQuery.nickname != null and attendeeQuery.nickname.trim() != \"\"'> " +
            "       and nickname like CONCAT(#{attendeeQuery.nickname}, '%') " +
            "   </if> " +
            "   <if test='attendeeQuery.phone != null and attendeeQuery.phone.trim() != \"\"'> " +
            "       and phone = #{attendeeQuery.phone} " +
            "   </if> " +
            "   <if test='attendeeQuery.sex != null and attendeeQuery.sex.trim() != \"\"'> " +
            "       and sex = #{attendeeQuery.sex} " +
            "   </if> " +
            "   <if test='attendeeQuery.time != null and attendeeQuery.time.trim() != \"\"'> " +
            "       and DATE_FORMAT(gmt_create, '%Y-%m-%d') = #{attendeeQuery.time} " +
            "   </if> " +
            "</where>" +
            "</script>")
    IPage<Attendee> findAttendeeList(Page<Attendee> page, @Param("attendeeQuery") AttendeeQuery attendeeQuery);



}
