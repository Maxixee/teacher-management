package com.example.ms_teachers_management.infrastructure.rest.instructormanagement.feign.mapper;

import com.example.ms_teachers_management.core.port.out.dto.SalaryDtoOutput;
import com.example.ms_teachers_management.core.port.out.dto.SubjectDtoOutput;
import com.example.ms_teachers_management.core.port.out.dto.TeacherDtoOutput;
import com.example.ms_teachers_management.core.port.out.dto.TeacherListDtoOutput;
import com.example.ms_teachers_management.infrastructure.rest.instructormanagement.feign.dto.InstructorList;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Slf4j
@Component
public class InstructorListToTeachersListOutputMapper {

    public TeacherListDtoOutput responseGetTeacherListMapper(InstructorList instructorList) {
        TeacherListDtoOutput teacherListDtoOutput = new TeacherListDtoOutput();

        if(CollectionUtils.isNotEmpty(instructorList.getInstructors())) {
            teacherListDtoOutput.setTeachers(instructorList.getInstructors().stream()
                    .filter(Objects::nonNull)
                    .map(instructor -> {
                        TeacherDtoOutput teacherDtoOutput = new TeacherDtoOutput();
                        teacherDtoOutput.setId(instructor.getId());
                        teacherDtoOutput.setName(instructor.getFullName());

                        SalaryDtoOutput salaryDtoOutput = new SalaryDtoOutput();
                        salaryDtoOutput.setAmount(instructor.getWage().getTotal());
                        salaryDtoOutput.setCurrency(instructor.getWage().getCurrency());

                        SubjectDtoOutput subjectDtoOutput = new SubjectDtoOutput();
                        subjectDtoOutput.setId(instructor.getCourse().getId());
                        subjectDtoOutput.setName(instructor.getCourse().getTitle());

                        teacherDtoOutput.setSalary(salaryDtoOutput);
                        teacherDtoOutput.setSubject(subjectDtoOutput);
                        return teacherDtoOutput;
                    }).toList());
        }

        return teacherListDtoOutput;
    }
}
