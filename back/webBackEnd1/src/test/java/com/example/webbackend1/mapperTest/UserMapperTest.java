package com.example.webbackend1.mapperTest;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.webbackend1.entity.User;
import com.example.webbackend1.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class UserMapperTest {
    @Mock
    private UserMapper userMapper;
    @Test
    void testSelectByLoginName() {
        // Prepare mock behavior
        User mockUser = new User();
        mockUser.setLoginName("testuser");
        when(userMapper.selectByLoginName("testuser")).thenReturn(mockUser);
        // Execute the method under test
        User user = userMapper.selectByLoginName("testuser");
        // Assert the results
        assertNotNull(user);
        assertEquals("testuser", user.getLoginName());
    }
    @Test
    void testSelectUserPage() {
        // Prepare mock data and behavior
        Page<User> page = new Page<>(1, 10);
        IPage<User> mockPage = mock(IPage.class);
        when(mockPage.getTotal()).thenReturn(20L); // Assume there are some records
        when(userMapper.selectUserPage(page, "")).thenReturn(mockPage);
        // Execute the method under test
        IPage<User> result = userMapper.selectUserPage(page, "");
        // Assert the results
        assertNotNull(result);
        assertTrue(result.getTotal() > 0);
    }
}
