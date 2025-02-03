package com.example.teacher.core.repository;

import com.example.kafka.contracts.Teacher;
import com.example.teacher.core.port.in.TeacherPortInDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface TeacherRepository {
    void save(TeacherPortInDto teacher);
}
