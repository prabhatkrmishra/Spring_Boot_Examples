package com.example.EventRegistration;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Scanner;

@SpringBootApplication
public class EventRegistrationApplication {

    public static void main(String[] args) {

        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContext.xml");

        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Graduation Ceremony Registration Application");

        CollegeEvent event = context.getBean("event", CollegeEvent.class);

        event.printEventDetails();

        while (true) {
            System.out.println("Do you want to register for the ceremony\n1. Yes\n2. No");
            int input = Integer.parseInt(scanner.nextLine());

            if (input == 1) {

                System.out.println("Please enter your name");
                String name = scanner.nextLine();

                System.out.println("Please enter your department");
                String dept = scanner.nextLine();

                System.out.println("In which year did you pass out?");
                int year = Integer.parseInt(scanner.nextLine());

                Attendee attendee = context.getBean("student", Attendee.class);
                attendee.setAttendeeDetails(name, dept, year);

                event.registerStudent(attendee);
                attendee.printRegistrationConfirmation();

            } else if (input == 2) {
                break;
            } else {
                System.out.println("Invalid Choice");
                return;
            }
        }

        System.out.println("No. of attendees registered are: " + event.getAttendeeCount());
        System.out.println("The list of attendees are:");

        for (Attendee a : event.getAllAttendees()) {
            System.out.println(a.getAttendeeName() + "    Reference id: @"
                    + Integer.toHexString(a.hashCode()));
        }

        context.close();
    }
}