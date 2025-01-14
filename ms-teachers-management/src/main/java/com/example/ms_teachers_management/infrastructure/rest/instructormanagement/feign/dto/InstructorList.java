package com.example.ms_teachers_management.infrastructure.rest.instructormanagement.feign.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class InstructorList {

    @JsonProperty("instructors")
    private List<Instructor> instructors;
}
