package com.example.StudentManagementSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * StudentManagementSystemApplication
 *
 * <p>Entry point for the Student Management System Spring Boot application.
 * The {@code @SpringBootApplication} annotation enables:
 * <ul>
 *   <li>Auto-configuration of Spring context</li>
 *   <li>Component scanning of all sub-packages</li>
 *   <li>Configuration properties support</li>
 * </ul>
 *
 * <p>Run this class to start the embedded Tomcat server on port 8080.
 */
@SpringBootApplication
public class StudentManagementSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudentManagementSystemApplication.class, args);
    }
}
