package com.example.teacher.api.event;

import com.example.kafka.contracts.Event;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;

public interface TeacherEventConsumer {

    @KafkaListener(topics = "${spring.kafka.topics.employee-info}")
    void consume(ConsumerRecord<String, Event> event);
}
