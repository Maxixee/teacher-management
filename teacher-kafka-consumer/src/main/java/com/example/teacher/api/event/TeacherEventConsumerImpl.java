package com.example.teacher.api.event;

import com.example.kafka.contracts.Event;
import com.example.teacher.core.port.in.TeacherPortInDto;
import com.example.teacher.core.service.TeacherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.stereotype.Component;

import java.util.List;

import com.example.teacher.infrastructure.mapper.TeacherMapper;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class TeacherEventConsumerImpl implements TeacherEventConsumer {

    private final TeacherService teacherService;

    public void consume(ConsumerRecord<String, Event> event) {
        log.info("[CONSUMER] - [TeacherEventConsumer.consume] - Evento consumido: {}", event);

        Event eventValue = event.value();
        List<TeacherPortInDto> teachers = eventValue.getTeachers().stream()
                .map(TeacherMapper::toDomain)
                .collect(Collectors.toList());

        if (!teachers.isEmpty()) {
            teacherService.processTeachers(teachers);
        } else {
            log.warn("[CONSUMER] - [TeacherEventConsumer.consume] - Lista de professores vazia ou nula.");
        }
    }
}
