package com.example.webbackend1.controllerTest;

import com.example.webbackend1.controller.RegisterController;
import com.example.webbackend1.entity.RegisterDTO;
import com.example.webbackend1.entity.Result;
import com.example.webbackend1.service.RegisterService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class RegisterControllerTest {
    @Mock
    private RegisterService registerService;
    @InjectMocks
    private RegisterController registerController;
    private MockMvc mockMvc;
    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(registerController).build();
    }
    @Test
    public void testRegister() throws Exception {
        // Create data transfer object for registration
        RegisterDTO registerDTO = new RegisterDTO("user", "user@example.com", "password123", "password123", "captcha123");
        Result expectedResponse = Result.success();
        // Setup the mocked service
        when(registerService.register(any(RegisterDTO.class))).thenReturn(expectedResponse);
        // Serialize the RegisterDTO to JSON
        String jsonDto = "{\"username\":\"user\",\"email\":\"user@example.com\",\"password\":\"password123\",\"confirmPassword\":\"password123\",\"captcha\":\"captcha123\"}";
        // Perform the post request and verify the response
        mockMvc.perform(post("/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonDto))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(0)); // Assuming the success code is 0
        // Verify service method was called
        verify(registerService, times(1)).register(any(RegisterDTO.class));
    }
}
