package com.example.kafka.contracts;

import lombok.Data;

import java.util.List;

@Data
public class Event {
    private String uuid;
    private String createdDate;
    private List<Teacher> teachers;
}
