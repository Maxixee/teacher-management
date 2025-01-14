package com.example.ms_teachers_management.core.application.service;

import com.example.ms_teachers_management.core.application.mapper.TeacherToInstructorCoreMapper;
import com.example.ms_teachers_management.core.port.in.TeacherPortIn;
import com.example.ms_teachers_management.core.port.in.dto.TeacherDto;
import com.example.ms_teachers_management.core.port.in.dto.TeacherListDto;
import com.example.ms_teachers_management.core.port.out.SendEventProducerPortOut;
import com.example.ms_teachers_management.core.port.out.TeacherManagerPortOut;
import com.example.ms_teachers_management.core.port.out.dto.TeacherDtoOutput;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TeacherService implements TeacherPortIn {

    private final TeacherManagerPortOut teacherManagerPortOut;
    private final SendEventProducerPortOut sendEventProducerPortOut;
    private final TeacherToInstructorCoreMapper teacherToInstructorCoreMapper;
    private final ModelMapper modelMapper;

    @Override
    public TeacherListDto listTeachers(String authorization, String origin) {

        log.info("[SERVICE - teacherManagerPortOut.getTeachers] - Executar a chamada da API TeacherManager");
        log.info(" service passou {}", origin);
        var teacherListOutput = teacherManagerPortOut.getTeachers(authorization, origin);
        log.info("[SERVICE - teacherManagerPortOut.getTeachers] - Chamada da API TeacherManager realizada com sucesso");

        sendEventProducerPortOut.sendTeacherInfoEvent(teacherListOutput);

        return modelMapper.map(teacherListOutput, TeacherListDto.class);
    }

    @Override
    public TeacherDto createTeacher(String authorization, String origin, TeacherDtoOutput teacherDtoOutput) {
        log.info("[SERVICE - teacherManagerPortOut.createTeachers] - Executar a chamada da API TeacherManager");
        log.info(" service passou {}", origin);
        var teacherOutput = teacherManagerPortOut.createTeacher(authorization, origin, teacherToInstructorCoreMapper.responseGetInstructorMapper(teacherDtoOutput));
        log.info("[SERVICE - teacherManagerPortOut.createTeachers] - Chamada da API TeacherManager realizada com sucesso");


        return modelMapper.map(teacherOutput, TeacherDto.class);
    }

    @Override
    public TeacherDto getTeacherById(String authorization, String origin, Integer id) {
        log.info("[SERVICE - teacherManagerPortOut.createTeachers] - Executar a chamada da API TeacherManager");
        log.info(" service passou {}", origin);
        var teacherOutput = teacherManagerPortOut.getTeacherById(authorization, origin, id);
        log.info("[SERVICE - teacherManagerPortOut.getTeachersById] - Chamada da API TeacherManager realizada com sucesso");

        return modelMapper.map(teacherOutput, TeacherDto.class);    }
}
