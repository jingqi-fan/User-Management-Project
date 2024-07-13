package com.example.webbackend1.controllerTest;

import com.example.webbackend1.controller.TenantController;
import com.example.webbackend1.entity.Attendee;
import com.example.webbackend1.entity.AttendeeQuery;
import com.example.webbackend1.entity.AttendeeResult;
import com.example.webbackend1.entity.Tenant;
import com.example.webbackend1.service.TenantService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class TenantControllerTest {

    @Mock
    private TenantService tenantService;

    @InjectMocks
    private TenantController tenantController;

    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(tenantController).build();
    }

    @Test
    void testInsertTenant() throws Exception {
        Tenant tenant = new Tenant(); // 假设设置了attendee的属性
        AttendeeResult expectedResponse = new AttendeeResult();

        when(tenantService.insertTenant(any(Tenant.class))).thenReturn(expectedResponse);

        mockMvc.perform(post("/tenant")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"name\": \"John Doe\" }"))
                .andExpect(status().isOk());

        verify(tenantService, times(1)).insertTenant(any(Tenant.class));
    }

    @Test
    void testDeleteTenant() throws Exception {
        AttendeeResult expectedResponse = new AttendeeResult();
        when(tenantService.deleteTenant(1)).thenReturn(expectedResponse);

        mockMvc.perform(delete("/tenant/1"))
                .andExpect(status().isOk());

        verify(tenantService, times(1)).deleteTenant(1);
    }

    @Test
    void testDeleteTenantMore() throws Exception {
        List<Integer> ids = Arrays.asList(1, 2, 3);
        AttendeeResult expectedResponse = new AttendeeResult();
        when(tenantService.deleteTenantMore(ids)).thenReturn(expectedResponse);

        mockMvc.perform(delete("/tenant")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("[1, 2, 3]"))
                .andExpect(status().isOk());

        verify(tenantService, times(1)).deleteTenantMore(ids);
    }

    @Test
    void testModifyTenant() throws Exception {
        Tenant tenant = new Tenant();
        AttendeeResult expectedResponse = new AttendeeResult();
        when(tenantService.modifyTenant(any(Tenant.class))).thenReturn(expectedResponse);

        mockMvc.perform(put("/tenant")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"name\": \"John Doe Updated\" }"))
                .andExpect(status().isOk());

        verify(tenantService, times(1)).modifyTenant(any(Tenant.class));
    }

    @Test
    void testGetAttendeeInfo() throws Exception {
        AttendeeResult expectedResponse = new AttendeeResult();
        when(tenantService.getTenantInfoById(1)).thenReturn(expectedResponse);

        mockMvc.perform(post("/tenant/1"))
                .andExpect(status().isOk());

        verify(tenantService, times(1)).getTenantInfoById(1);
    }
}
