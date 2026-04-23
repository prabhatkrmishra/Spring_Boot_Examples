package com.example.Cinemaxify;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Scanner;

@SpringBootApplication
public class CinemaxifyApplication {

    public static void main(String[] args) {

        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContext.xml");

        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Cinemaxify Application");
        System.out.println("Please select the member you want the plan for:");
        System.out.println("1. Self\n2. Spouse");

        String userType = "";
        int userChoice = scanner.nextInt();
        scanner.nextLine();

        switch (userChoice) {
            case 1 -> userType = "self";
            case 2 -> userType = "spouse";
            case 3 -> {
                System.out.println("Exiting...");
                return;
            }
            default -> {
                System.out.println("Invalid choice.");
                return;
            }
        }

        System.out.println("Please select your plan:");
        System.out.println("1. Normal\n2. Premium");

        String planType = "";
        int planChoice = scanner.nextInt();
        scanner.nextLine();

        switch (planChoice) {
            case 1 -> planType = "Normal";
            case 2 -> planType = "Premium";
            default -> {
                System.out.println("Invalid choice.");
                return;
            }
        }

        String beanId = userType + planType;
        User user = (User) context.getBean(beanId);

        System.out.println("Please enter your name:");
        String name = scanner.nextLine();

        System.out.println("Please enter your age:");
        int age = scanner.nextInt();

        System.out.println("Please enter your contact:");
        Long contact = scanner.nextLong();
        scanner.nextLine();

        System.out.println("Please enter your address:");
        String address = scanner.nextLine();

        user.setUserDetails(name, age, contact, address);
        user.getUserDetails();
    }
}