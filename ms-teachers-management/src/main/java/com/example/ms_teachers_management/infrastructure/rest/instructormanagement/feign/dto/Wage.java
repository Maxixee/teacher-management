package com.example.ms_teachers_management.infrastructure.rest.instructormanagement.feign.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Wage {
    @JsonProperty("total")
    private Number total = null;
    @JsonProperty("currency")
    private String currency = null;
}
