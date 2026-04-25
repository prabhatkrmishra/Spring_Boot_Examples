package com.example.Vaccination;

public class Mother implements User {

    private String name;
    private Integer age;
    private boolean isVaccinated = false;

    private TimeAndLocation timeAndLocation;
    private Vaccine vaccine;

    public void setTimeAndLocation(TimeAndLocation timeAndLocation) {
        this.timeAndLocation = timeAndLocation;
    }

    public void setVaccine(Vaccine vaccine) {
        this.vaccine = vaccine;
    }

    @Override
    public Vaccine getVaccineDetails() {
        return vaccine;
    }

    @Override
    public void setUserDetails(String name, int age, TimeAndLocation timeAndLocation) {
        this.name = name;
        this.age = age;
        this.timeAndLocation = timeAndLocation;
    }

    @Override
    public void setAppointment() {
        if (isVaccinated) {
            System.out.println("User is already Vaccinated");
            return;
        }

        isVaccinated = true;

        System.out.println("Hello " + name + " your appointment has been fixed for "
                + vaccine.getType() + " Vaccine on "
                + timeAndLocation.getDetails());
    }

    @Override
    public boolean IsVaccinated() {
        return isVaccinated;
    }
}