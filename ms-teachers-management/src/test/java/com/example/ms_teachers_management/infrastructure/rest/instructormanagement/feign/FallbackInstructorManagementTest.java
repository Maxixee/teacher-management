package com.example.ms_teachers_management.infrastructure.rest.instructormanagement.feign;

import com.example.ms_teachers_management.infrastructure.rest.instructormanagement.feign.dto.Instructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FallbackInstructorManagementTest {

    private FallbackInstructorManagement fallbackInstructorManagement;
    private InstructorManagementFeign instructorManagementFeign;

    @BeforeEach
    public void setUp() {
        fallbackInstructorManagement = new FallbackInstructorManagement();
        instructorManagementFeign = fallbackInstructorManagement.create(new Throwable("Simulated error"));
    }

    @Test
    public void testGetAllInstructors_ShouldThrowRuntimeException() {
        String authorization = "Bearer token";
        String origin = "http://example.com";

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            instructorManagementFeign.getAllInstructors(authorization, origin);
        });

        assert exception.getMessage().equals("Internal Server Error - Erro inesperado no processamento da requisição. Por favor, tente novamente em alguns instantes");
    }

    @Test
    public void testSaveInstructor_ShouldThrowRuntimeException() {
        String authorization = "Bearer token";
        String origin = "http://example.com";
        Instructor instructor = new Instructor();

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            instructorManagementFeign.saveInstructor(authorization, origin, instructor);
        });

        assert exception.getMessage().equals("Internal Server Error - Erro inesperado no processamento da requisição. Por favor, tente novamente em alguns instantes");
    }

    @Test
    public void testGetInstructorById_ShouldThrowRuntimeException() {
        String authorization = "Bearer token";
        String origin = "http://example.com";
        Integer id = 1;

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            instructorManagementFeign.getInstructorById(authorization, origin, id);
        });

        assert exception.getMessage().equals("Internal Server Error - Erro inesperado no processamento da requisição. Por favor, tente novamente em alguns instantes");
    }
}