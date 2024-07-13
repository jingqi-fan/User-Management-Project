package com.example.webbackend1.controllerTest;

import com.example.webbackend1.controller.UserController;
import com.example.webbackend1.entity.User;
import com.example.webbackend1.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void getUserByIdTest() throws Exception {
        User user = new User(1, "loginName", "userName", "password", "male", "email@example.com", "address");

        when(userService.getUserById(1)).thenReturn(user);

        mockMvc.perform(get("/user/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.loginName").value("loginName"))
                .andExpect(jsonPath("$.userName").value("userName"))
                .andExpect(jsonPath("$.email").value("email@example.com"));
    }

    @Test
    public void updateUserTest() throws Exception {
        User user = new User(1, "loginName", "userName", "password", "male", "email@example.com", "address");
        when(userService.updateUser(any(User.class))).thenReturn(true);

        String userJson = "{\"id\":1,\"loginName\":\"loginName\",\"userName\":\"userName\",\"password\":\"password\",\"sex\":\"male\",\"email\":\"email@example.com\",\"address\":\"address\"}";

        mockMvc.perform(put("/user/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(true));
    }
}
