package com.example.teacher.core.port.in;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SalaryPortInDto {
    @JsonProperty("amount")
    private Number amount = null;
    @JsonProperty("currency")
    private String currency = null;
}
