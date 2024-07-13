package com.example.webbackend1.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.webbackend1.entity.User;
import com.example.webbackend1.mapper.UserMapper;
import com.example.webbackend1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserById(Integer id) {
        return userMapper.selectById(id);
    }

    @Override
    public boolean updateUser(User user) {
        return userMapper.updateById(user) > 0;
    }

    @Override
    public IPage<User> getUserPage(Page<User> page, String keyword) {
        return userMapper.selectUserPage(page, keyword);
    }
}
