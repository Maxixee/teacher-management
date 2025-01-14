package com.example.ms_teachers_management.infrastructure.rest.instructormanagement;

import com.example.ms_teachers_management.core.port.out.TeacherManagerPortOut;
import com.example.ms_teachers_management.core.port.out.dto.TeacherDtoOutput;
import com.example.ms_teachers_management.core.port.out.dto.TeacherListDtoOutput;
import com.example.ms_teachers_management.infrastructure.rest.instructormanagement.feign.InstructorManagementFeign;
import com.example.ms_teachers_management.infrastructure.rest.instructormanagement.feign.dto.Instructor;
import com.example.ms_teachers_management.infrastructure.rest.instructormanagement.feign.mapper.InstructorListToTeachersListOutputMapper;
import com.example.ms_teachers_management.infrastructure.rest.instructormanagement.feign.mapper.InstructorToTeacherMapper;
import com.example.ms_teachers_management.infrastructure.rest.instructormanagement.feign.mapper.TeacherToInstructorMapper;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class InstructorManagerIntegrator implements TeacherManagerPortOut {

    private final InstructorListToTeachersListOutputMapper instructorListToTeachersListOutputMapper;
    private final InstructorToTeacherMapper instructorToTeacherMapper;
    private final InstructorManagementFeign feign;

    @Override
    public TeacherListDtoOutput getTeachers(String authorization, String origin) {
        log.info("[ADAPTER OUT - TeacherManagerIntegrator.getTeacher] - Enviando requisição para a operação getTeachers da API Teachers Management");
        try{
            var response = feign.getAllInstructors(authorization, origin);
            log.info("integrator passou {}", origin);
            log.info("[ADAPTER OUT - TeacherManagerIntegrator.getTeacher] - Chamada de GetTeachers da API Teachers Management realizada com sucesso");

            return instructorListToTeachersListOutputMapper.responseGetTeacherListMapper(response);
        } catch (FeignException e) {
            log.info("[ADAPTER OUT - TeacherManagerIntegrator.getTeacher] -  Falha na chamada de GetTeachers da API Teachers Management . erro {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public TeacherDtoOutput createTeacher(String authorization, String origin, Instructor instructor) {
        log.info("[ADAPTER OUT - TeacherManagerIntegrator.createTeacher] - Enviando requisição para a operação createTeachers da API Teachers Management");
        try{
            var response = feign.saveInstructor(authorization, origin, instructor);
            log.info("integrator passou {}", origin);
            log.info("[ADAPTER OUT - TeacherManagerIntegrator.createTeacher] - Chamada de CreateTeachers da API Teachers Management realizada com sucesso");

            return instructorToTeacherMapper.responseGetTeacherMapper(response);
        }catch(FeignException e){
            log.info("[ADAPTER OUT - TeacherManagerIntegrator.createTeacher] -  Falha na chamada de CreateTeachers da API Teachers Management . erro {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public TeacherDtoOutput getTeacherById(String authorization, String origin, Integer id) {
        log.info("[ADAPTER OUT - TeacherManagerIntegrator.getTeacherById] - Enviando requisição para a operação getTeachersById da API Teachers Management");
        try{
            var response = feign.getInstructorById(authorization, origin, id);
            log.info("integrator passou {}", origin);
            log.info("[ADAPTER OUT - TeacherManagerIntegrator.getTeacherById] - Chamada de GetTeachersById da API Teachers Management realizada com sucesso");

            return instructorToTeacherMapper.responseGetTeacherMapper(response);
        } catch (FeignException e) {
            log.info("[ADAPTER OUT - TeacherManagerIntegrator.GetTeacherById] -  Falha na chamada de GetTeachersById da API Teachers Management . erro {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
