package com.example.teacher.core.repository;

import com.example.teacher.core.port.out.TeacherPortOutDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherJpaRepository extends JpaRepository<TeacherPortOutDto, Integer> {
}
