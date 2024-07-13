package com.example.webbackend1.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.webbackend1.entity.User;

public interface UserService {
    User getUserById(Integer id);
    boolean updateUser(User user);
    IPage<User> getUserPage(Page<User> page, String keyword);
}
