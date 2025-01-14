package com.example.kafka.contracts;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Salary {
    @JsonProperty("amount")
    private Number amount = null;
    @JsonProperty("currency")
    private String currency = null;
}
