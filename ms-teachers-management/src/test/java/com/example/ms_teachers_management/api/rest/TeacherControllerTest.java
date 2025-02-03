package com.example.ms_teachers_management.api.rest;


import com.example.ms_teachers_management.core.application.service.TeacherService;
import com.example.ms_teachers_management.core.port.in.dto.TeacherDto;
import com.example.ms_teachers_management.core.port.in.dto.TeacherListDto;
import com.example.ms_teachers_management.core.port.out.dto.TeacherDtoOutput;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class TeacherControllerTest {

    @Mock
    private TeacherService teacherService;

    @InjectMocks
    private TeacherController teacherController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    private String authorization;
    private String origin;
    private TeacherDto teacherDto;
    private TeacherListDto teacherListDto;
    private TeacherDtoOutput teacherDtoOutput;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(teacherController).build();
        objectMapper = new ObjectMapper();

        authorization = "Bearer token";
        origin = "origin";
        teacherDto = new TeacherDto();
        teacherListDto = new TeacherListDto();
        teacherDtoOutput = new TeacherDtoOutput();
    }

    @Test
    void testGetTeachers() throws Exception {
        when(teacherService.listTeachers(authorization, origin)).thenReturn(teacherListDto);

        mockMvc.perform(get("/teachers")
                        .header("Authorization", authorization)
                        .header("origin", origin))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(teacherListDto)));

        verify(teacherService).listTeachers(authorization, origin);
    }

    @Test
    void testCreateTeacher() throws Exception {
        when(teacherService.createTeacher(eq(authorization), eq(origin), any(TeacherDtoOutput.class)))
                .thenReturn(teacherDto);

        mockMvc.perform(post("/teachers")
                        .header("Authorization", authorization)
                        .header("origin", origin)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(teacherDtoOutput)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(teacherDto)));

        verify(teacherService).createTeacher(eq(authorization), eq(origin), any(TeacherDtoOutput.class));
    }

    @Test
    void testGetTeacherById() throws Exception {
        Integer id = 1;
        when(teacherService.getTeacherById(authorization, origin, id)).thenReturn(teacherDto);

        mockMvc.perform(get("/teachers/{id}", id)
                        .header("Authorization", authorization)
                        .header("origin", origin))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(teacherDto)));

        verify(teacherService).getTeacherById(authorization, origin, id);
    }


}