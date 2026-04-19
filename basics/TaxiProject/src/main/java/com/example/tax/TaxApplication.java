package com.example.tax;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Scanner;

@SpringBootApplication
@ImportResource("classpath:applicationContext.xml")
public class TaxApplication {

    public static void main(String[] args) {

        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContext.xml");

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("Welcome to the Tax Payment Application");
            System.out.println("Please select which tax you want to pay:");
            System.out.println("1. Income");
            System.out.println("2. Property");
            System.out.println("3. Exit");

            int choice = sc.nextInt();
            String taxChoice = "";

            switch (choice) {
                case 1:
                    taxChoice = "incomeTax";
                    break;
                case 2:
                    taxChoice = "propertyTax";
                    break;
                case 3:
                    System.out.println("Exiting...");
                    context.close();
                    return;
                default:
                    System.out.println("Invalid choice");
                    continue;
            }

            Tax tax = (Tax) context.getBean(taxChoice);

            if (tax.isTaxPayed()) {
                System.out.println("You have already payed " + tax.getTaxType() + " tax.");
                continue;
            }

            System.out.println("Please enter your Income/Property value:");
            double amount = sc.nextDouble();

            tax.setTaxableAmount(amount);
            tax.calculateTaxAmount();

            System.out.println("You have selected " + tax.getTaxType()
                    + " tax and your tax amount is: " + tax.getTaxAmount());

            System.out.println("Do you want to pay the tax:");
            System.out.println("1. Yes");
            System.out.println("2. Exit");

            int payChoice = sc.nextInt();

            if (payChoice == 1) {
                tax.payTax();
            }
        }
    }
}