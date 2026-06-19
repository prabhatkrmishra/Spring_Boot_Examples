package com.example.StudentManagementSystem.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Student
 *
 * <p>JPA entity that maps to the {@code students} table in the MySQL database.
 * Lombok annotations are used to eliminate boilerplate:
 * <ul>
 *   <li>{@code @Getter}        – generates getters for all fields</li>
 *   <li>{@code @Setter}        – generates setters for all fields</li>
 *   <li>{@code @NoArgsConstructor}  – generates a default (no-arg) constructor required by JPA</li>
 *   <li>{@code @AllArgsConstructor} – generates a constructor accepting all fields</li>
 * </ul>
 *
 * <p>The primary key {@code sid} is a manually assigned integer; no
 * {@code @GeneratedValue} strategy is applied per the assignment specification.
 */
@Entity
@Table(name = "students")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    /** Unique student identifier (primary key). */
    @Id
    private int sid;

    /** Full name of the student. */
    private String name;

    /** Age of the student in years. */
    private int age;

    /** Course or programme the student is enrolled in. */
    private String course;

    /** Residential address of the student. */
    private String address;

    /** Email address of the student. */
    private String emailId;

    /** Contact phone number of the student. */
    private String contact;
}
