package com.project.crudlearning.application.models;

import jakarta.persistence.*;

@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long id;
    @Column
    private String name;
    @Column
    private int age;
    @Column
    private String gender;
    @Column
    private int grade;

    public Student() {
    }

    public Student(String name, int age, String gender, int grade) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.grade = grade;
    }

    public Long getId() {
        return id;
    }

    /*public void setId(Long id) {
        this.id = id;
    }*/

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(Short age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(Short grade) {
        this.grade = grade;
    }
}
