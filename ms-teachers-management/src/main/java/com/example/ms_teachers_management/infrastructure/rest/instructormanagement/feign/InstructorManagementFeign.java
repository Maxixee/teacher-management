package com.example.ms_teachers_management.infrastructure.rest.instructormanagement.feign;

import com.example.ms_teachers_management.core.port.out.dto.TeacherDtoOutput;
import com.example.ms_teachers_management.core.port.out.dto.TeacherListDtoOutput;
import com.example.ms_teachers_management.infrastructure.rest.instructormanagement.feign.dto.Instructor;
import com.example.ms_teachers_management.infrastructure.rest.instructormanagement.feign.dto.InstructorList;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "instructor-management", fallbackFactory = FallbackInstructorManagement.class)
public interface InstructorManagementFeign {

    @GetMapping(value = "/instructors", consumes = "application/json; charset=utf-8")
    InstructorList getAllInstructors(
            @RequestHeader(value = "Authorization") String authorization,
            @RequestHeader(value = "origin") String origin
    );

    @PostMapping(value = "/instructors")
    Instructor saveInstructor(
            @RequestHeader(value = "Authorization") String authorization,
            @RequestHeader(value = "origin") String origin,
            @RequestBody Instructor instructor
            );

    @GetMapping(value = "/instructors/{id}")
    Instructor getInstructorById(
            @RequestHeader(value = "Authorization") String authorization,
            @RequestHeader(value = "origin") String origin,
            @PathVariable Integer id
            );
}
