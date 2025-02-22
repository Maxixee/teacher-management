package com.example.ms_teachers_management.core.port.in.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SubjectDto {
    @JsonProperty("id")
    private Integer id = null;
    @JsonProperty("name")
    private String name = null;
}
