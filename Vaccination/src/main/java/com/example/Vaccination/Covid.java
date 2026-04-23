package com.example.Vaccination;

public class Covid implements Vaccine {
    @Override
    public String getType() {
        return "Covid";
    }
}