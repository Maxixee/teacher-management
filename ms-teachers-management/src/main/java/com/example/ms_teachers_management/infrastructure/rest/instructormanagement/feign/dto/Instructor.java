package com.example.ms_teachers_management.infrastructure.rest.instructormanagement.feign.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Instructor {
    @JsonProperty("id")
    private Integer id = null;
    @JsonProperty("fullName")
    private String fullName = null;
    @JsonProperty("course")
    private Course course = null;
    @JsonProperty("wage")
    private Wage wage = null;
}
