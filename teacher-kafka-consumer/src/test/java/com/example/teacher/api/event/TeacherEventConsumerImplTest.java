package com.example.teacher.api.event;

import com.example.kafka.contracts.Event;
import com.example.kafka.contracts.Salary;
import com.example.kafka.contracts.Subject;
import com.example.kafka.contracts.Teacher;
import com.example.teacher.core.service.TeacherService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

public class TeacherEventConsumerImplTest {

    @Mock
    private TeacherService teacherService;

    @InjectMocks
    private TeacherEventConsumerImpl teacherEventConsumer;

    private List<Teacher> teachers;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        Subject mathSubject = new Subject();
        mathSubject.setId(1);
        mathSubject.setName("Math");

        Salary salary5000 = new Salary();
        salary5000.setAmount(5000.0);
        salary5000.setCurrency("USD");

        Teacher teacher1 = new Teacher();
        teacher1.setId(1);
        teacher1.setName("John Doe");
        teacher1.setSubject(mathSubject);
        teacher1.setSalary(salary5000);

        teachers = Arrays.asList(
                teacher1);
    }

    @Test
    public void testConsumeWithNonEmptyTeachersList() {
        Event event = new Event();
        event.setTeachers(teachers);

        ConsumerRecord<String, Event> record = new ConsumerRecord<>("topic", 0, 0, "key", event);

        teacherEventConsumer.consume(record);

        verify(teacherService, times(1)).processTeachers(anyList());
    }

    @Test
    public void testConsumeWithEmptyTeachersList() {
        Event event = new Event();
        event.setTeachers(Collections.emptyList());

        ConsumerRecord<String, Event> record = new ConsumerRecord<>("topic", 0, 0, "key", event);

        teacherEventConsumer.consume(record);

        verify(teacherService, never()).processTeachers(anyList());
    }
}