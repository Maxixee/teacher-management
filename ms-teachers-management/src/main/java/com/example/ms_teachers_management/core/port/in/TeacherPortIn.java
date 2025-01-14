package com.example.ms_teachers_management.core.port.in;

import com.example.ms_teachers_management.core.port.in.dto.TeacherDto;
import com.example.ms_teachers_management.core.port.in.dto.TeacherListDto;
import com.example.ms_teachers_management.core.port.out.dto.TeacherDtoOutput;

public interface TeacherPortIn {
    //isso é como se fosse uma interface do Service

    TeacherListDto listTeachers(String authorization, String origin);
    TeacherDto createTeacher(String authorization, String origin, TeacherDtoOutput teacherDtoOutput);
    TeacherDto getTeacherById(String authorization, String origin, Integer id);
}
