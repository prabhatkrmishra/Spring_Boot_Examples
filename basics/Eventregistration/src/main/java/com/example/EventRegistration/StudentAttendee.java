package com.example.EventRegistration;

public class StudentAttendee implements Attendee {

    private String name;
    private int batch;
    private String department;
    private String referenceId;

    @Override
    public void setAttendeeDetails(String name, String department, int batch) {
        this.name = name;
        this.department = department;
        this.batch = batch;
        this.referenceId = "@" + Integer.toHexString(this.hashCode());
    }

    @Override
    public void printRegistrationConfirmation() {
        System.out.println("Hi " + name + ", your registration for the Graduation Ceremony is successful");
    }

    @Override
    public String getAttendeeName() {
        return name;
    }

    public void init() {
        System.out.println("StudentAttendee bean initialized");
    }
}