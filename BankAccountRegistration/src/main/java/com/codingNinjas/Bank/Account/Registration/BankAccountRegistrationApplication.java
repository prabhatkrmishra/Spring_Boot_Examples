package com.codingNinjas.Bank.Account.Registration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;

import java.util.Scanner;
import java.util.UUID;

@SpringBootApplication
@ImportResource("classpath:ApplicationContext.xml")
public class BankAccountRegistrationApplication {

    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(
                BankAccountRegistrationApplication.class,
                new String[]{"--spring.main.web-application-type=none"}
        );

        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Account Registration Application!");
        System.out.println("Please enter Your name?");
        String name = scanner.nextLine();

        myUser user = (myUser) context.getBean("myUser");
        user.setUserDetails(name);

        System.out.println("Do you want to add account");
        System.out.println("1. Yes\n2. No");

        int choice = scanner.nextInt();

        if (choice != 1) {
            System.out.println("Application ends");
            context.close(); // important
            return;
        }

        boolean addMore = true;

        while (addMore) {

            System.out.println("Please select the account type");
            System.out.println("1. Current\n2. Savings");

            int type = scanner.nextInt();

            Account account;

            if (type == 1) {
                account = (Account) context.getBean("currentAccount");
            } else {
                account = (Account) context.getBean("savingsAccount");
            }

            System.out.println("Enter the opening balance");
            double balance = scanner.nextDouble();

            account.addBalance(balance);
            user.addAccount(account);

            System.out.println("Do you want to add more accounts");
            System.out.println("1. Yes\n2. No");

            int more = scanner.nextInt();
            addMore = (more == 1);
        }

        System.out.println("Hi " + user.getName() + ", here is the list of your accounts:");

        for (Account acc : user.getAllAccounts()) {
            String refId = UUID.randomUUID().toString().substring(0, 8);

            System.out.println(
                    acc.getAccountType() +
                            " : opening balance - " + acc.getBalance() +
                            " Reference Id @" + refId
            );
        }

        // CRITICAL: triggers destroy()
        context.close();
    }
}