package com.example.tax;

public class PropertyTax implements Tax {

    double taxableAmount;
    double taxAmount;
    boolean isTaxPayed;

    public PropertyTax() {
        this.isTaxPayed = false;
    }

    @Override
    public void setTaxableAmount(double amount) {
        this.taxableAmount = amount;
    }

    @Override
    public void calculateTaxAmount() {
        this.taxAmount = this.taxableAmount * 0.05;
    }

    @Override
    public double getTaxAmount() {
        return taxAmount;
    }

    @Override
    public String getTaxType() {
        return "property";
    }

    @Override
    public boolean isTaxPayed() {
        return isTaxPayed;
    }

    @Override
    public void payTax() {
        if (!isTaxPayed) {
            System.out.println("Hi, your property tax is paid.");
            isTaxPayed = true;
        } else {
            System.out.println("Tax already paid.");
        }
    }
}