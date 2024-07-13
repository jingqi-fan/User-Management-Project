package com.example.webbackend1.controllerTest;

import com.example.webbackend1.controller.AttendeeController;
import com.example.webbackend1.entity.Attendee;
import com.example.webbackend1.entity.AttendeeQuery;
import com.example.webbackend1.entity.AttendeeResult;
import com.example.webbackend1.service.AttendeeService;
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
public class AttendeeControllerTest {

    @Mock
    private AttendeeService attendeeService;

    @InjectMocks
    private AttendeeController attendeeController;

    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(attendeeController).build();
    }

    @Test
    void testInsertAttendee() throws Exception {
        Attendee attendee = new Attendee(); // 假设设置了attendee的属性
        AttendeeResult expectedResponse = new AttendeeResult();

        when(attendeeService.insertAttendee(any(Attendee.class))).thenReturn(expectedResponse);

        mockMvc.perform(post("/attendee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"name\": \"John Doe\" }")) // JSON表示Attendee对象
                .andExpect(status().isOk());

        verify(attendeeService, times(1)).insertAttendee(any(Attendee.class));
    }

    @Test
    void testDeleteAttendee() throws Exception {
        AttendeeResult expectedResponse = new AttendeeResult();
        when(attendeeService.deleteAttendee(1)).thenReturn(expectedResponse);

        mockMvc.perform(delete("/attendee/1"))
                .andExpect(status().isOk());

        verify(attendeeService, times(1)).deleteAttendee(1);
    }

    @Test
    void testDeleteAttendeeMore() throws Exception {
        List<Integer> ids = Arrays.asList(1, 2, 3);
        AttendeeResult expectedResponse = new AttendeeResult();
        when(attendeeService.deleteAttendeeMore(ids)).thenReturn(expectedResponse);

        mockMvc.perform(delete("/attendee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("[1, 2, 3]"))
                .andExpect(status().isOk());

        verify(attendeeService, times(1)).deleteAttendeeMore(ids);
    }

    @Test
    void testModifyAttendee() throws Exception {
        Attendee attendee = new Attendee(); // 假设设置了attendee的属性
        AttendeeResult expectedResponse = new AttendeeResult();
        when(attendeeService.modifyAttendee(any(Attendee.class))).thenReturn(expectedResponse);

        mockMvc.perform(put("/attendee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"name\": \"John Doe Updated\" }"))
                .andExpect(status().isOk());

        verify(attendeeService, times(1)).modifyAttendee(any(Attendee.class));
    }


    @Test
    void testGetAttendeeInfo() throws Exception {
        AttendeeResult expectedResponse = new AttendeeResult();
        when(attendeeService.getAttendeeInfoById(1)).thenReturn(expectedResponse);

        mockMvc.perform(post("/attendee/1"))
                .andExpect(status().isOk());

        verify(attendeeService, times(1)).getAttendeeInfoById(1);
    }


}
