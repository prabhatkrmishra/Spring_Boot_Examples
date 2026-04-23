package com.example.Vaccination;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Scanner;

@SpringBootApplication
public class VaccinationApplication {

    public static void main(String[] args) {

        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContext.xml");

        Scanner scanner = new Scanner(System.in);

        while (true) {

            System.out.println("Welcome to the Vaccination Application");
            System.out.println("Please choose your vaccine preference:");
            System.out.println("1. Covid\n2. Polio\n3. Typhoid");

            int vaccineChoice = scanner.nextInt();
            scanner.nextLine();

            String vaccineType = "";

            switch (vaccineChoice) {
                case 1 -> vaccineType = "Covid";
                case 2 -> vaccineType = "Polio";
                case 3 -> vaccineType = "Typhoid";
                default -> {
                    System.out.println("Invalid choice");
                    continue;
                }
            }

            System.out.println("Whom do you want to vaccinate");
            System.out.println("1. Father\n2. Mother\n3. Self\n4. Spouse\n5. Exit");

            int userChoice = scanner.nextInt();
            scanner.nextLine();

            String userType = "";

            switch (userChoice) {
                case 1 -> userType = "father";
                case 2 -> userType = "mother";
                case 3 -> userType = "self";
                case 4 -> userType = "spouse";
                case 5 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> {
                    System.out.println("Invalid choice");
                    continue;
                }
            }

            String beanId = userType + vaccineType;

            User user = (User) context.getBean(beanId);
            TimeAndLocation timeAndLocation =
                    (TimeAndLocation) context.getBean("timeAndLocation");

            if (user.IsVaccinated()) {
                System.out.println("User is already Vaccinated");
            } else {

                System.out.println("Please enter " + capitalize(userType) + " details:");

                System.out.print("Name: ");
                String name = scanner.nextLine();

                System.out.print("Age: ");
                int age = scanner.nextInt();
                scanner.nextLine();

                System.out.print("Appointment date (YYYY-MM-DD): ");
                String date = scanner.nextLine();

                System.out.print("Appointment time (HH:MM AM/PM): ");
                String time = scanner.nextLine();

                System.out.print("Appointment location: ");
                String location = scanner.nextLine();

                timeAndLocation.setDetails(time, location, date);

                user.setUserDetails(name, age, timeAndLocation);
                user.setAppointment();
            }

            System.out.println("Do you want to register for someone Else");
            System.out.println("1. Yes\n2. No");

            int again = scanner.nextInt();
            scanner.nextLine();

            if (again == 2) {
                break;
            }
        }
    }

    private static String capitalize(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
}