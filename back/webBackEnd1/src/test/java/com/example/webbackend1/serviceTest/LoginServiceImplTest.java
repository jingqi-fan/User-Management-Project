package com.example.webbackend1.serviceTest;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.webbackend1.entity.LoginDTO;
import com.example.webbackend1.entity.Result;
import com.example.webbackend1.entity.User;
import com.example.webbackend1.mapper.UserMapper;
import com.example.webbackend1.service.LoginServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

class LoginServiceImplTest {

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private LoginServiceImpl loginService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testLoginNameEmpty() {
        Result result = loginService.login(new LoginDTO("", "password"));
        assertEquals(400, result.getCode());
        assertEquals("账号不能为空", result.getMessage());
    }

    @Test
    void testPasswordEmpty() {
        Result result = loginService.login(new LoginDTO("loginName", ""));
        assertEquals(400, result.getCode());
        assertEquals("密码不能为空", result.getMessage());
    }

    @Test
    void testUserNotFound() {
        when(userMapper.selectOne(any(QueryWrapper.class))).thenReturn(null);
        Result result = loginService.login(new LoginDTO("loginName", "password"));
        assertEquals(400, result.getCode());
        assertEquals("该账号尚未注册", result.getMessage());
    }

    @Test
    void testPasswordIncorrect() {
        User user = new User(1, "loginName", "userName", "password", "M", "email@example.com", "address");
        when(userMapper.selectOne(any(QueryWrapper.class))).thenReturn(user);
        Result result = loginService.login(new LoginDTO("loginName", "wrongPassword"));
        assertEquals(401, result.getCode());
        assertEquals("账号或密错误", result.getMessage());
    }

    @Test
    void testLoginSuccessful() {
        User user = new User(1, "loginName", "userName", "password", "M", "email@example.com", "address");
        when(userMapper.selectOne(any(QueryWrapper.class))).thenReturn(user);
        Result result = loginService.login(new LoginDTO("loginName", "password"));
        assertEquals(200, result.getCode());
        assertEquals(user, result.getData());
    }
}
