package com.example.Customers;

public class PaymentDepartment implements CustomerCare {

    private String department = "Payment Department";
    private String customerName;
    private String issue;
    private double refId = 751.0;

    @Override
    public String getDepartment() {
        return department;
    }

    @Override
    public void getService() {
        System.out.println("Welcome " + customerName + ", you have reached the payments department");
    }

    @Override
    public void setCustomerName(String name) {
        this.customerName = name;
    }

    @Override
    public void setProblem(String problem) {
        this.issue = problem;
    }

    @Override
    public void getProblem() {
        System.out.println("Dear " + customerName + " your issue is registered with Payments. Reference id: " + refId);
    }
}