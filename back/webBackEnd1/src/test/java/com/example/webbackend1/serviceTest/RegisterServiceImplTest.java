package com.example.webbackend1.serviceTest;

import com.example.webbackend1.entity.RegisterDTO;
import com.example.webbackend1.entity.Result;
import com.example.webbackend1.entity.User;
import com.example.webbackend1.mapper.UserMapper;
import com.example.webbackend1.service.RegisterServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

class RegisterServiceImplTest {

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private RegisterServiceImpl registerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testUsernameAlreadyExists() {
        when(userMapper.selectByLoginName("existingUser")).thenReturn(new User());
        RegisterDTO dto = new RegisterDTO("existingUser", "email@example.com", "password", "password", "KDQU");
        Result result = registerService.register(dto);
        assertEquals(400, result.getCode());
        assertEquals("Username already exists", result.getMessage());
    }

    @Test
    void testPasswordsDoNotMatch() {
        when(userMapper.selectByLoginName("newUser")).thenReturn(null);
        RegisterDTO dto = new RegisterDTO("newUser", "email@example.com", "password", "password123", "KDQU");
        Result result = registerService.register(dto);
        assertEquals(400, result.getCode());
        assertEquals("Passwords do not match", result.getMessage());
    }

    @Test
    void testInvalidCaptcha() {
        when(userMapper.selectByLoginName("newUser")).thenReturn(null);
        RegisterDTO dto = new RegisterDTO("newUser", "email@example.com", "password", "password", "wrong");
        Result result = registerService.register(dto);
        assertEquals(400, result.getCode());
        assertEquals("Invalid captcha", result.getMessage());
    }

    @Test
    void testSuccessfulRegistration() {
        when(userMapper.selectByLoginName("newUser")).thenReturn(null);
        when(userMapper.insert(any(User.class))).thenReturn(1);
        RegisterDTO dto = new RegisterDTO("newUser", "email@example.com", "password", "password", "KDQU");
        Result result = registerService.register(dto);
        assertEquals(200, result.getCode());
        assertEquals("Registration successful", result.getMessage());
    }
}
