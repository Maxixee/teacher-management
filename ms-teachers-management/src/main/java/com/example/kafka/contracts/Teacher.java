package com.example.kafka.contracts;

import com.example.ms_teachers_management.core.port.in.dto.SalaryDto;
import com.example.ms_teachers_management.core.port.in.dto.SubjectDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Teacher {
    @JsonProperty("id")
    private Integer id = null;
    @JsonProperty("name")
    private String name = null;
    @JsonProperty("subject")
    private SubjectDto subject = null;
    @JsonProperty("salary")
    private SalaryDto salary = null;
}
