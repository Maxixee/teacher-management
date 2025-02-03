package com.example.teacher.core.service;

import com.example.teacher.core.port.in.TeacherPortInDto;
import com.example.teacher.core.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherService {

    private final TeacherRepository teacherRepository;

    public void processTeachers(List<TeacherPortInDto> teachers) {
        for (TeacherPortInDto teacher : teachers) {
            teacherRepository.save(teacher);
        }
    }
}
