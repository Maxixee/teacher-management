package com.example.teacher.core.port.in;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SubjectPortInDto {
    @JsonProperty("id")
    private Integer id = null;
    @JsonProperty("name")
    private String name = null;
}
