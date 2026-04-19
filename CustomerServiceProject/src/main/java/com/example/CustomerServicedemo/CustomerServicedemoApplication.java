package com.example.CustomerServicedemo;

import com.example.Customers.CustomerCare;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Scanner;

@SpringBootApplication
@ImportResource("classpath:ApplicationContext.xml")
public class CustomerServicedemoApplication {

    public static void main(String[] args) {

        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContext.xml");

        Scanner sc = new Scanner(System.in);

        System.out.println("Welcome to our Customer Care application");

        System.out.print("Please enter your name: ");
        String name = sc.nextLine();

        System.out.println("Thanks for reaching us " + name);

        System.out.println("Please select a department to connect to:");
        System.out.println("1. Payment Department");
        System.out.println("2. Query Department");
        System.out.println("3. Sales Department");
        System.out.println("0. Exit");

        int choice = sc.nextInt();
        sc.nextLine();

        if (choice == 0) {
            System.out.println("Exiting...");
            return;
        }

        String beanId = "";

        switch (choice) {
            case 1:
                beanId = "paymentDepartment";
                break;
            case 2:
                beanId = "queryDepartment";
                break;
            case 3:
                beanId = "salesDepartment";
                break;
        }

        CustomerCare dept = (CustomerCare) context.getBean(beanId);

        dept.setCustomerName(name);
        dept.getService();

        String issue = sc.nextLine();
        System.out.println(issue);

        dept.setProblem(issue);
        dept.getProblem();

        context.close();
    }
}