package com.codingNinjas.Bank.Account.Registration;

import org.springframework.stereotype.Component;
import org.springframework.context.annotation.Scope;
import jakarta.annotation.PostConstruct;

@Component("currentAccount")
@Scope("prototype")
public class currentAccount implements Account {

    private double amount = 0;

    @PostConstruct
    public void init() {
        System.out.println("Current Account has been created I'm the init() method");
    }

    @Override
    public String getAccountType() {
        return "Current Account";
    }

    @Override
    public void addBalance(double balance) {
        this.amount += balance;
    }

    @Override
    public double getBalance() {
        return amount;
    }
}