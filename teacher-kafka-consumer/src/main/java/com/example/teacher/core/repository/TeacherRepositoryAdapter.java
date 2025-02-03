package com.example.teacher.core.repository;

import com.example.teacher.core.port.in.TeacherPortInDto;
import com.example.teacher.core.port.out.TeacherPortOutDto;
import com.example.teacher.infrastructure.mapper.TeacherMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TeacherRepositoryAdapter implements TeacherRepository {

    private final TeacherJpaRepository teacherJpaRepository;

    @Override
    public void save(TeacherPortInDto teacher) {
        // Mapeia a entidade do domínio para a entidade JPA (se necessário)
        TeacherPortOutDto teacherEntity = TeacherMapper.toEntity(teacher);
        teacherJpaRepository.save(teacherEntity);
    }
}
