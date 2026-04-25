package com.example.Vaccination;

public class TimeAndLocation {

    private String timeSlot;
    private String location;
    private String date;

    public void setTimeSlot(String timeSlot) {
        this.timeSlot = timeSlot;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDetails() {
        return timeSlot + " at " + location + " on " + date;
    }
}