package com.example.ms_teachers_management.core.application.mapper;

import com.example.ms_teachers_management.core.port.out.dto.SalaryDtoOutput;
import com.example.ms_teachers_management.core.port.out.dto.SubjectDtoOutput;
import com.example.ms_teachers_management.core.port.out.dto.TeacherDtoOutput;
import com.example.ms_teachers_management.infrastructure.rest.instructormanagement.feign.dto.Course;
import com.example.ms_teachers_management.infrastructure.rest.instructormanagement.feign.dto.Instructor;
import com.example.ms_teachers_management.infrastructure.rest.instructormanagement.feign.dto.Wage;
import org.springframework.stereotype.Component;

@Component
public class TeacherToInstructorCoreMapper {

    public Instructor responseGetInstructorMapper(TeacherDtoOutput teacherDtoOutput) {
        if (teacherDtoOutput == null) {
            return null;
        }

        Instructor instructor = new Instructor();
        instructor.setId(teacherDtoOutput.getId());
        instructor.setFullName(teacherDtoOutput.getName());

        Wage wage = new Wage();
        SalaryDtoOutput salaryDtoOutput = teacherDtoOutput.getSalary();
        if (salaryDtoOutput != null) {
            wage.setTotal(salaryDtoOutput.getAmount());
            wage.setCurrency(salaryDtoOutput.getCurrency());
        }
        instructor.setWage(wage);

        Course course = new Course();
        SubjectDtoOutput subjectDtoOutput = teacherDtoOutput.getSubject();
        if (subjectDtoOutput != null) {
            course.setId(subjectDtoOutput.getId());
            course.setTitle(subjectDtoOutput.getName());
        }
        instructor.setCourse(course);

        return instructor;
    }
}

