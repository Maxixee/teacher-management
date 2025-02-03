package com.example.teacher.infrastructure.mapper;

import com.example.kafka.contracts.Teacher;
import com.example.teacher.core.port.in.SalaryPortInDto;
import com.example.teacher.core.port.in.SubjectPortInDto;
import com.example.teacher.core.port.in.TeacherPortInDto;
import com.example.teacher.core.port.out.TeacherPortOutDto;

public class TeacherMapper {

    public static TeacherPortInDto toDomain(Teacher teacherEvent) {
        TeacherPortInDto teacher = new TeacherPortInDto();
        teacher.setId(teacherEvent.getId());
        teacher.setName(teacherEvent.getName());

        SubjectPortInDto subject = new SubjectPortInDto();
        subject.setId(teacherEvent.getSubject().getId());
        subject.setName(teacherEvent.getSubject().getName());
        teacher.setSubject(subject);

        SalaryPortInDto salary = new SalaryPortInDto();
        salary.setAmount(teacherEvent.getSalary().getAmount());
        salary.setCurrency(teacherEvent.getSalary().getCurrency());
        teacher.setSalary(salary);
        return teacher;
    }

    public static TeacherPortOutDto toEntity(TeacherPortInDto teacher) {
        TeacherPortOutDto teacherEntity = new TeacherPortOutDto();
        teacherEntity.setName(teacher.getName());
        teacherEntity.setSalary(teacher.getSalary().getAmount().toString());
        teacherEntity.setSalary_Currency(teacher.getSalary().getCurrency());
        teacherEntity.setSubject(teacher.getSubject().getName());

        return teacherEntity;
    }
}
