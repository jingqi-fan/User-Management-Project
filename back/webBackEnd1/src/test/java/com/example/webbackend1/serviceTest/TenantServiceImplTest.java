package com.example.webbackend1.serviceTest;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.webbackend1.entity.AttendeeQuery;
import com.example.webbackend1.entity.AttendeeResult;
import com.example.webbackend1.entity.Tenant;
import com.example.webbackend1.mapper.TenantMapper;
import com.example.webbackend1.service.TenantServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class TenantServiceImplTest {

    @Mock
    private TenantMapper tenantMapper;

    @InjectMocks
    private TenantServiceImpl tenantService;

    @Test
    void testInsertTenantSuccessful() {
        Tenant tenant = new Tenant();
        tenant.setUsername("newUser");
        when(tenantMapper.selectOne(any())).thenReturn(null);
        when(tenantMapper.insert(any(Tenant.class))).thenReturn(1);
        AttendeeResult result = tenantService.insertTenant(tenant);
        assertTrue(result.getSuccess());
    }

    @Test
    void testDeleteTenant() {
        when(tenantMapper.deleteById(1)).thenReturn(1);
        AttendeeResult result = tenantService.deleteTenant(1);
        assertTrue(result.getSuccess());
    }

    @Test
    void testDeleteTenantMore() {
        List<Integer> ids = Arrays.asList(1, 2, 3);
        when(tenantMapper.deleteBatchIds(ids)).thenReturn(ids.size());
        AttendeeResult result = tenantService.deleteTenantMore(ids);
        assertTrue(result.getSuccess());
    }

    @Test
    void testModifyTenant() {
        Tenant tenant = new Tenant();
        tenant.setId(1);
        when(tenantMapper.updateById(any(Tenant.class))).thenReturn(1);
        AttendeeResult result = tenantService.modifyTenant(tenant);
        assertTrue(result.getSuccess());
    }

    @Test
    void testFindTenantList() {
        Page<Tenant> page = new Page<>(1, 5);
        AttendeeQuery query = new AttendeeQuery();
        when(tenantMapper.findTenantList(any(Page.class), eq(query))).thenReturn(page);
        AttendeeResult result = tenantService.findTenantList(1, 5, query);
        assertNotNull(result.getData().get("userList"));
    }
}
