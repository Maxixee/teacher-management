package com.example.ms_teachers_management.core.port.out.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class TeacherListDtoOutput {
    @JsonProperty("teachers")
    List<TeacherDtoOutput> teachers;
}
