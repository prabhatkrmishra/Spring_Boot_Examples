package com.example.tax;

public interface Tax {
    void setTaxableAmount(double amount);
    void calculateTaxAmount();
    double getTaxAmount();
    String getTaxType();
    boolean isTaxPayed();
    void payTax();
}