package com.example.ms_teachers_management.core.application.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.example.ms_teachers_management.core.application.mapper.TeacherToInstructorCoreMapper;
import com.example.ms_teachers_management.core.port.in.dto.TeacherDto;
import com.example.ms_teachers_management.core.port.in.dto.TeacherListDto;
import com.example.ms_teachers_management.core.port.out.SendEventProducerPortOut;
import com.example.ms_teachers_management.core.port.out.TeacherManagerPortOut;
import com.example.ms_teachers_management.core.port.out.dto.TeacherDtoOutput;
import com.example.ms_teachers_management.core.port.out.dto.TeacherListDtoOutput;
import com.example.ms_teachers_management.infrastructure.rest.instructormanagement.feign.dto.Instructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import org.junit.jupiter.api.BeforeEach;

@ExtendWith(MockitoExtension.class)
class TeacherServiceTest {

    @Mock
    private TeacherManagerPortOut teacherManagerPortOut;

    @Mock
    private SendEventProducerPortOut sendEventProducerPortOut;

    @Mock
    private TeacherToInstructorCoreMapper teacherToInstructorCoreMapper;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private TeacherService teacherService;

    private final String authorization = "auth-token";
    private final String origin = "test-origin";

    private TeacherListDtoOutput teacherListOutput;
    private TeacherListDto teacherListDto;
    private TeacherDtoOutput teacherDtoOutput;
    private Instructor instructorCore;
    private TeacherDto teacherDto;

    @BeforeEach
    void setUp() {
        teacherListOutput = new TeacherListDtoOutput();
        teacherListDto = new TeacherListDto();
        teacherDtoOutput = new TeacherDtoOutput();
        instructorCore = new Instructor();
        teacherDto = new TeacherDto();
    }

    @Test
    void shouldReturnAListOfTeachers() {
        when(teacherManagerPortOut.getTeachers(authorization, origin)).thenReturn(teacherListOutput);
        when(modelMapper.map(teacherListOutput, TeacherListDto.class)).thenReturn(teacherListDto);

        var result = teacherService.listTeachers(authorization, origin);

        assertNotNull(result);
        assertEquals(teacherListDto, result);
        verify(teacherManagerPortOut).getTeachers(authorization, origin);
        verify(sendEventProducerPortOut).sendTeacherInfoEvent(teacherListOutput);
        verify(modelMapper).map(teacherListOutput, TeacherListDto.class);
    }

    @Test
    void whenCallsListTeachersShouldReturnException() {
        when(teacherManagerPortOut.getTeachers(authorization, origin)).thenThrow(new RuntimeException("Erro na API"));

        Exception exception = assertThrows(RuntimeException.class, () -> {
            teacherService.listTeachers(authorization, origin);
        });

        assertEquals("Erro na API", exception.getMessage());
    }

    @Test
    void shouldCreateANewTeacher() {
        when(teacherToInstructorCoreMapper.responseGetInstructorMapper(teacherDtoOutput)).thenReturn(instructorCore);
        when(teacherManagerPortOut.createTeacher(authorization, origin, instructorCore)).thenReturn(teacherDtoOutput);
        when(modelMapper.map(teacherDtoOutput, TeacherDto.class)).thenReturn(teacherDto);

        var result = teacherService.createTeacher(authorization, origin, teacherDtoOutput);

        assertNotNull(result);
        assertEquals(teacherDto, result);
        verify(teacherToInstructorCoreMapper).responseGetInstructorMapper(teacherDtoOutput);
        verify(teacherManagerPortOut).createTeacher(authorization, origin, instructorCore);
        verify(modelMapper).map(teacherDtoOutput, TeacherDto.class);
    }

    @Test
    void whenTryCreateTeacherThrowsException() {
        when(teacherToInstructorCoreMapper.responseGetInstructorMapper(teacherDtoOutput)).thenThrow(new RuntimeException("Erro ao mapear"));

        Exception exception = assertThrows(RuntimeException.class, () -> {
            teacherService.createTeacher(authorization, origin, teacherDtoOutput);
        });

        assertEquals("Erro ao mapear", exception.getMessage());
    }

    @Test
    void shouldGetTeacherById() {
        int id = 1;
        when(teacherManagerPortOut.getTeacherById(authorization, origin, id)).thenReturn(teacherDtoOutput);
        when(modelMapper.map(teacherDtoOutput, TeacherDto.class)).thenReturn(teacherDto);

        var result = teacherService.getTeacherById(authorization, origin, id);

        assertNotNull(result);
        assertEquals(teacherDto, result);
        verify(teacherManagerPortOut).getTeacherById(authorization, origin, id);
        verify(modelMapper).map(teacherDtoOutput, TeacherDto.class);
    }

    @Test
    void whenTryGetTeacherByIdThrowsException() {
        int id = 1;
        when(teacherManagerPortOut.getTeacherById(authorization, origin, id)).thenThrow(new RuntimeException("Professor não encontrado"));

        Exception exception = assertThrows(RuntimeException.class, () -> {
            teacherService.getTeacherById(authorization, origin, id);
        });

        assertEquals("Professor não encontrado", exception.getMessage());
    }
}
