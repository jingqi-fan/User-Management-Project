package com.example.webbackend1.serviceTest;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.webbackend1.entity.Attendee;
import com.example.webbackend1.entity.AttendeeQuery;
import com.example.webbackend1.entity.AttendeeResult;
import com.example.webbackend1.mapper.AttendeeMapper;
import com.example.webbackend1.service.AttendeeServiceImpl;
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
public class AttendeeServiceImplTest {

    @Mock
    private AttendeeMapper attendeeMapper;

    @InjectMocks
    private AttendeeServiceImpl attendeeService;

    @Test
    void testInsertAttendeeSuccessful() {
        Attendee attendee = new Attendee();
        attendee.setUsername("newUser");
        when(attendeeMapper.selectOne(any())).thenReturn(null);
        when(attendeeMapper.insert(any(Attendee.class))).thenReturn(1);
        AttendeeResult result = attendeeService.insertAttendee(attendee);
        assertTrue(result.getSuccess());
    }

    @Test
    void testDeleteAttendee() {
        when(attendeeMapper.deleteById(1)).thenReturn(1);
        AttendeeResult result = attendeeService.deleteAttendee(1);
        assertTrue(result.getSuccess());
    }
    @Test
    void testDeleteAttendeeMore() {
        List<Integer> ids = Arrays.asList(1, 2, 3);
        when(attendeeMapper.deleteBatchIds(ids)).thenReturn(ids.size());
        AttendeeResult result = attendeeService.deleteAttendeeMore(ids);
        assertTrue(result.getSuccess());
    }

    @Test
    void testModifyAttendee() {
        Attendee attendee = new Attendee();
        attendee.setId(1);
        when(attendeeMapper.updateById(any(Attendee.class))).thenReturn(1);
        AttendeeResult result = attendeeService.modifyAttendee(attendee);
        assertTrue(result.getSuccess());
    }

    @Test
    void testFindAttendeeList() {
        Page<Attendee> page = new Page<>(1, 5);
        AttendeeQuery query = new AttendeeQuery();
        when(attendeeMapper.findAttendeeList(any(Page.class), eq(query))).thenReturn(page);
        AttendeeResult result = attendeeService.findAttendeeList(1, 5, query);
        assertNotNull(result.getData().get("userList"));
    }
}
