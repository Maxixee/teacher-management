package com.example.ms_teachers_management.core.port.out;

import com.example.ms_teachers_management.core.port.out.dto.TeacherDtoOutput;
import com.example.ms_teachers_management.core.port.out.dto.TeacherListDtoOutput;
import com.example.ms_teachers_management.infrastructure.rest.instructormanagement.feign.dto.Instructor;

public interface TeacherManagerPortOut {
    // essa é uma interface que prepara as coisas que vem de fora da api
    TeacherListDtoOutput getTeachers(String authorization, String origin);
    TeacherDtoOutput createTeacher(String authorization, String origin, Instructor instructor);
    TeacherDtoOutput getTeacherById(String authorization, String origin, Integer id);
}
