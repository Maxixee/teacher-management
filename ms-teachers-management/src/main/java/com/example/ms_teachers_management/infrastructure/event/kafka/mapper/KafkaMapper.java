package com.example.ms_teachers_management.infrastructure.event.kafka.mapper;

import com.example.kafka.contracts.Event;
import com.example.kafka.contracts.Teacher;
import com.example.ms_teachers_management.core.port.out.dto.TeacherListDtoOutput;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Slf4j
@Component
public class KafkaMapper {

    private final ModelMapper modelMapper = new ModelMapper();

    public Event createEvent(TeacherListDtoOutput teacherList) {
        Event event = new Event();

        event.setUuid(UUID.randomUUID().toString());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        event.setCreatedDate(formatter.format(LocalDateTime.now()));

        event.setTeachers(mapListOfTeachers(teacherList));
        return event;
    }

    private List<Teacher> mapListOfTeachers(TeacherListDtoOutput teacherListDtoOutput) {
        return teacherListDtoOutput.getTeachers()
                .stream()
                .map(teacher -> modelMapper.map(teacher, Teacher.class))
                .toList();
    }
}
