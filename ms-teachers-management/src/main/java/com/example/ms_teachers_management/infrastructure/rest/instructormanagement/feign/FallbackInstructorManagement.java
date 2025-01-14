package com.example.ms_teachers_management.infrastructure.rest.instructormanagement.feign;

import com.example.ms_teachers_management.core.port.out.dto.TeacherDtoOutput;
import com.example.ms_teachers_management.infrastructure.rest.instructormanagement.feign.dto.Instructor;
import com.example.ms_teachers_management.infrastructure.rest.instructormanagement.feign.dto.InstructorList;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@Component
public class FallbackInstructorManagement implements FallbackFactory<InstructorManagementFeign> {

    @Override
    public InstructorManagementFeign create(Throwable cause) {
        return new InstructorManagementFeign() {
            @Override
            public InstructorList getAllInstructors(@RequestHeader(value = "Authorization") String authorization,
                                                    @RequestHeader(value = "origin") String origin) {
                throw new RuntimeException("Internal Server Error - Erro inesperado no processamento da requisição. Por favor, tente novamente em alguns instantes");
            }

            @Override
            public Instructor saveInstructor(@RequestHeader(value = "Authorization") String authorization,
                                             @RequestHeader(value = "origin") String origin,
                                             @RequestBody Instructor instructor) {
                throw new RuntimeException("Internal Server Error - Erro inesperado no processamento da requisição. Por favor, tente novamente em alguns instantes");
            }

            @Override
            public Instructor getInstructorById(
                    @RequestHeader(value = "Authorization") String authorization,
                    @RequestHeader(value = "origin") String origin,
                    @RequestBody Integer id
            ){
                throw new RuntimeException("Internal Server Error - Erro inesperado no processamento da requisição. Por favor, tente novamente em alguns instantes");
            }
        };
    }
}
