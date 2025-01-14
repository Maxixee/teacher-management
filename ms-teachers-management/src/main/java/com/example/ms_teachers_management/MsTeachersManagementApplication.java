package com.example.ms_teachers_management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MsTeachersManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsTeachersManagementApplication.class, args);
	}

}
