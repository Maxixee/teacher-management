package com.example.ms_teachers_management.core.port.out.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SalaryDtoOutput {
    @JsonProperty("amount")
    private Number amount = null;
    @JsonProperty("currency")
    private String currency = null;
}
