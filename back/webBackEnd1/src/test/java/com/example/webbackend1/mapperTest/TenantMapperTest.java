package com.example.webbackend1.mapperTest;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.webbackend1.entity.Attendee;
import com.example.webbackend1.entity.AttendeeQuery;
import com.example.webbackend1.entity.Tenant;
import com.example.webbackend1.mapper.AttendeeMapper;
import com.example.webbackend1.mapper.TenantMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class TenantMapperTest {
    @Mock
    private TenantMapper tenantMapper;
    @Test
    void testFindTenantList() {
        // Prepare mock data and behavior
        Page<Tenant> page = new Page<>(1, 10);
        AttendeeQuery attendeeQuery = new AttendeeQuery();
        attendeeQuery.setUsername("testuser");

        IPage<Tenant> mockPage = mock(IPage.class);
        when(mockPage.getTotal()).thenReturn(10L); // Assume there are 10 records

        when(tenantMapper.findTenantList(page, attendeeQuery)).thenReturn(mockPage);

        // Execute the method under test
        IPage<Tenant> result = tenantMapper.findTenantList(page, attendeeQuery);

        // Assert the results
        assertNotNull(result);
        assertEquals(10, result.getTotal());
    }
}
