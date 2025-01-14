package com.example.ms_teachers_management.core.port.in.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class TeacherListDto {
    @JsonProperty("teachers")
    List<TeacherDto> teachers;
}
