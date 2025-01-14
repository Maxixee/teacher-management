package com.example.ms_teachers_management.core.port.in.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SalaryDto {
    @JsonProperty("amount")
    private Number amount = null;
    @JsonProperty("currency")
    private String currency = null;
}
