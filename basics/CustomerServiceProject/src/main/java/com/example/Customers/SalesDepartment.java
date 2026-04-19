package com.example.Customers;

public class SalesDepartment implements CustomerCare {

    private String department = "Sales Department";
    private String customerName;
    private String issue;
    private double refId = 753.0;

    @Override
    public String getDepartment() {
        return department;
    }

    @Override
    public void getService() {
        System.out.println("Welcome " + customerName + ", you have reached the sales department");
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
        System.out.println("Dear " + customerName + " your issue is registered with Sales. Reference id: " + refId);
    }
}