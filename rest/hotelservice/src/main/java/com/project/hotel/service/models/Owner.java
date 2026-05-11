package com.project.hotel.service.models;

import org.springframework.stereotype.Component;

@Component
public class Owner {

    private Integer id = 0;
    private String firstName;
    private String lastName;
    private Integer age;
    private String gender;
    private String phone;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void printDetails() {
        System.out.println(id + " | " + firstName + " | " + lastName + " | " + age + " | " + gender + " | " + phone);
    }
}
