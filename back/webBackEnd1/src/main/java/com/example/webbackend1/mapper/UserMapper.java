package com.example.webbackend1.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.webbackend1.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

/**
 * Mapper interface for accessing the User data in the database.
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    /**
     * Select a single User by their login name.
     * This method uses MyBatis annotations to define the SQL directly in the code,
     * though XML mapping or dynamic SQL can also be used.
     *
     * @param loginName the login name of the User
     * @return the User object if found, or null if not found
     */
    @Select("SELECT * FROM user WHERE login_name = #{loginName}")
    User selectByLoginName(String loginName);

    IPage<User> selectUserPage(Page<User> page, String keyword);

}
