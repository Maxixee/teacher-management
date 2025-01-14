package com.example.ms_teachers_management.core.port.out.dto;

import com.example.ms_teachers_management.infrastructure.rest.instructormanagement.feign.dto.Course;
import com.example.ms_teachers_management.infrastructure.rest.instructormanagement.feign.dto.Wage;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class InstructorDtoOutput {
    @JsonProperty("id")
    private Integer id = null;
    @JsonProperty("fullName")
    private String fullName = null;
    @JsonProperty("course")
    private Course course = null;
    @JsonProperty("wage")
    private Wage wage = null;
}
