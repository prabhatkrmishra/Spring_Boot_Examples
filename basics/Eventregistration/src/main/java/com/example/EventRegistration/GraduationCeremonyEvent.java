package com.example.EventRegistration;

import java.util.ArrayList;
import java.util.List;

public class GraduationCeremonyEvent implements CollegeEvent {

    private String name = "Graduation Ceremony";
    private String address = "Auditorium";
    private String time = "10AM";
    private String date = "12 Nov 2023";

    private int count = 0;
    private List<Attendee> eventAttendees = new ArrayList<>();

    private Attendee attendee; // setter injected

    @Override
    public void registerStudent(Attendee user) {
        eventAttendees.add(user);
        count++;
    }

    @Override
    public List<Attendee> getAllAttendees() {
        return eventAttendees;
    }

    @Override
    public void printEventDetails() {
        System.out.println("The Graduation Ceremony details are as follows:");
        System.out.println("Venue: " + address);
        System.out.println("Time: " + time);
        System.out.println("Date: " + date);
    }

    @Override
    public int getAttendeeCount() {
        return count;
    }

    @Override
    public void setAttendee(Attendee attendee) {
        this.attendee = attendee;
    }
}