package com.example.Customers;

public class QueryDepartment implements CustomerCare {

    private String department = "Query Department";
    private String customerName;
    private String issue;
    private double refId = 752.0;

    @Override
    public String getDepartment() {
        return department;
    }

    @Override
    public void getService() {
        System.out.println("Welcome " + customerName + ", you have reached the query department");
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
        System.out.println("Dear " + customerName + " your issue is registered with Query. Reference id: " + refId);
    }
}