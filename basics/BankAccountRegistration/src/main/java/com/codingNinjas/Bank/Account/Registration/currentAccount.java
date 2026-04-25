package com.codingNinjas.Bank.Account.Registration;

public class currentAccount implements Account {

    private double amount = 0;

    // INIT
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