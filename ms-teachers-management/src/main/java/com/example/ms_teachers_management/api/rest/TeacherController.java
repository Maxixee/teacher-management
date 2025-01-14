package com.example.ms_teachers_management.api.rest;

import com.example.ms_teachers_management.core.port.in.TeacherPortIn;
import com.example.ms_teachers_management.core.port.in.dto.TeacherDto;
import com.example.ms_teachers_management.core.port.in.dto.TeacherListDto;
import com.example.ms_teachers_management.core.port.out.dto.TeacherDtoOutput;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class TeacherController {

    private final TeacherPortIn teacherService;

    @GetMapping("/teachers")
    public ResponseEntity<TeacherListDto> getTeachers(
            @RequestHeader(value = "Authorization") String authorization,
            @RequestHeader(value = "origin") String origin
    ) {
        log.info("controller passou {}", origin);
        return ResponseEntity.ok(teacherService.listTeachers(authorization, origin));
    }

    @PostMapping("/teachers")
    public ResponseEntity<TeacherDto> createTeachers(
            @RequestHeader(value = "Authorization") String authorization,
            @RequestHeader(value = "origin") String origin,
            @RequestBody TeacherDtoOutput teacherDtoOutput
            ){
        log.info("controller passou {}", origin);
        return ResponseEntity.status(HttpStatus.CREATED).body(teacherService.createTeacher(authorization, origin, teacherDtoOutput));
    }

    @GetMapping("/teachers/{id}")
    public ResponseEntity<TeacherDto> getTeacherById(
            @RequestHeader(value = "Authorization") String authorization,
            @RequestHeader(value = "origin") String origin,
            @PathVariable Integer id
    ){
        log.info("controller passou {}", origin);
        return ResponseEntity.ok(teacherService.getTeacherById(authorization, origin, id));
    }
}
