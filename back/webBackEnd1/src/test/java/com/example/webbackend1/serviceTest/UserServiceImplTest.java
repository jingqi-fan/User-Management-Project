package com.example.webbackend1.serviceTest;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.webbackend1.entity.User;
import com.example.webbackend1.mapper.UserMapper;
import com.example.webbackend1.service.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class UserServiceImplTest {

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getUserById() {
        User user = new User();
        user.setId(1);
        when(userMapper.selectById(1)).thenReturn(user);
        User result = userService.getUserById(1);
        assertEquals(user, result);
        verify(userMapper).selectById(1);
    }

    @Test
    void updateUser() {
        User user = new User();
        user.setId(1);
        when(userMapper.updateById(user)).thenReturn(1);
        assertTrue(userService.updateUser(user));
    }

    @Test
    void getUserPage() {
        Page<User> page = new Page<>();
        String keyword = "test";
        IPage<User> expectedPage = page;
        when(userMapper.selectUserPage(page, keyword)).thenReturn(expectedPage);
        IPage<User> result = userService.getUserPage(page, keyword);
        assertEquals(expectedPage, result);
    }
}
