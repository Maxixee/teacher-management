package com.example.teacher.core.port.in;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TeacherPortInDto {
    @JsonProperty("id")
    private Integer id = null;
    @JsonProperty("name")
    private String name = null;
    @JsonProperty("subject")
    private SubjectPortInDto subject = null;
    @JsonProperty("salary")
    private SalaryPortInDto salary = null;
}
