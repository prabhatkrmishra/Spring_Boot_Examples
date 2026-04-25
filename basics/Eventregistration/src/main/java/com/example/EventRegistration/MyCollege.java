package com.example.EventRegistration;

public class MyCollege implements College {

    private String name = "ABC College";
    private CollegeEvent event;

    public void setEvent(CollegeEvent event) {
        this.event = event;
    }

    @Override
    public String getCollegeName() {
        return name;
    }

    @Override
    public CollegeEvent getEvent() {
        return event;
    }

    public void init() {
        System.out.println("MyCollege bean initialized");
    }

    public void destroy() {
        System.out.println("MyCollege bean destroyed");
    }
}