package com.example.teacher.core.service;

import com.example.teacher.core.port.in.SalaryPortInDto;
import com.example.teacher.core.port.in.SubjectPortInDto;
import com.example.teacher.core.port.in.TeacherPortInDto;
import com.example.teacher.core.repository.TeacherRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TeacherServiceTest {

    @Mock
    private TeacherRepository teacherRepository;

    @InjectMocks
    private TeacherService teacherService;

    private List<TeacherPortInDto> teachers;

    @BeforeEach
    void setUp() {
        SubjectPortInDto mathSubject = new SubjectPortInDto();
        mathSubject.setId(1);
        mathSubject.setName("Math");

        SubjectPortInDto scienceSubject = new SubjectPortInDto();
        scienceSubject.setId(2);
        scienceSubject.setName("Science");

        SalaryPortInDto salary5000 = new SalaryPortInDto();
        salary5000.setAmount(5000.0);
        salary5000.setCurrency("USD");

        SalaryPortInDto salary6000 = new SalaryPortInDto();
        salary6000.setAmount(6000.0);
        salary6000.setCurrency("USD");

        TeacherPortInDto teacher1 = new TeacherPortInDto();
        teacher1.setId(1);
        teacher1.setName("John Doe");
        teacher1.setSubject(mathSubject);
        teacher1.setSalary(salary5000);
        TeacherPortInDto teacher2 = new TeacherPortInDto();
        teacher2.setId(1);
        teacher2.setName("Jane Smith");
        teacher2.setSubject(scienceSubject);
        teacher2.setSalary(salary6000);

        teachers = Arrays.asList(
                teacher1, teacher2
        );
    }

    @Test
    void testProcessTeachers_ShouldCallSaveForEachTeacher() {
        teacherService.processTeachers(teachers);

        verify(teacherRepository, times(1)).save(teachers.get(0));
        verify(teacherRepository, times(1)).save(teachers.get(1));
        verify(teacherRepository, times(teachers.size())).save(any(TeacherPortInDto.class));
    }

    @Test
    void testProcessTeachers_WithEmptyList_ShouldNotCallSave() {
        List<TeacherPortInDto> emptyList = List.of();

        teacherService.processTeachers(emptyList);

        verify(teacherRepository, never()).save(any(TeacherPortInDto.class));
    }
}