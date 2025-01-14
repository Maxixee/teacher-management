package com.example.ms_teachers_management.infrastructure.rest.instructormanagement.feign.mapper;

import com.example.ms_teachers_management.core.port.out.dto.SalaryDtoOutput;
import com.example.ms_teachers_management.core.port.out.dto.SubjectDtoOutput;
import com.example.ms_teachers_management.core.port.out.dto.TeacherDtoOutput;
import com.example.ms_teachers_management.infrastructure.rest.instructormanagement.feign.dto.Instructor;
import com.example.ms_teachers_management.infrastructure.rest.instructormanagement.feign.dto.Wage;
import com.example.ms_teachers_management.infrastructure.rest.instructormanagement.feign.dto.Course;
import org.springframework.stereotype.Component;

@Component
public class InstructorToTeacherMapper {

    public TeacherDtoOutput responseGetTeacherMapper(Instructor instructor) {
        if (instructor == null) {
            return null;
        }

        TeacherDtoOutput teacherDtoOutput = new TeacherDtoOutput();
        teacherDtoOutput.setId(instructor.getId());
        teacherDtoOutput.setName(instructor.getFullName());

        SalaryDtoOutput salaryDtoOutput = new SalaryDtoOutput();
        Wage wage = instructor.getWage();
        if (wage != null) {
            salaryDtoOutput.setAmount(wage.getTotal());
            salaryDtoOutput.setCurrency(wage.getCurrency());
        }
        teacherDtoOutput.setSalary(salaryDtoOutput);

        SubjectDtoOutput subjectDtoOutput = new SubjectDtoOutput();
        Course course = instructor.getCourse();
        if (course != null) {
            subjectDtoOutput.setId(course.getId());
            subjectDtoOutput.setName(course.getTitle());
        }
        teacherDtoOutput.setSubject(subjectDtoOutput);

        return teacherDtoOutput;
    }
}

