package com.example.ms_teachers_management.core.port.out;

import com.example.ms_teachers_management.core.port.out.dto.TeacherListDtoOutput;

public interface SendEventProducerPortOut {

    void sendTeacherInfoEvent(TeacherListDtoOutput teacherList);
}
