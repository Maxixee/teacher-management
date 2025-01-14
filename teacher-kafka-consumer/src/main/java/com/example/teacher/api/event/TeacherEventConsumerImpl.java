package com.example.teacher.api.event;

import com.example.kafka.contracts.Event;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class TeacherEventConsumerImpl implements TeacherEventConsumer {

    @Override
    public void consume(ConsumerRecord<String, Event> event) {
        log.info("[CONSUMER] - [TeacherEventConsumer.consume] - Evento consumido: {}", event);
    }
}
