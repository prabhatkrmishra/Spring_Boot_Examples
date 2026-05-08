package com.example.CarServicePart_1.domain;
import org.springframework.stereotype.Component;

@Component
public class Car implements Vehicle {

    String RegisterationNumber;
    String CarName;
    String CarDetails;
    String CarWork;
    Integer CarId;

    public Integer getCarId() {
        return CarId;
    }

    public void setCarId(Integer carId) {
        CarId = carId;
    }

    public String getCarWork() {
        return CarWork;
    }

    public void setCarWork(String carWork) {
        CarWork = carWork;
    }

    public String getCarDetails() {
        return CarDetails;
    }

    public void setCarDetails(String carDetails) {
        CarDetails = carDetails;
    }

    public String getCarName() {
        return CarName;
    }

    public void setCarName(String carName) {
        CarName = carName;
    }

    public String getRegisterationNumber() {
        return RegisterationNumber;
    }

    public void setRegisterationNumber(String registerationNumber) {
        RegisterationNumber = registerationNumber;
    }

    @Override
    public Boolean saveVehicleDetails() {
        return true;
    }

    @Override
    public void createVehicle(String RegistrationNumber, String CarName, String CarDetails, String WorkDone) {
        this.RegisterationNumber = RegistrationNumber;
        this.CarName = CarName;
        this.CarDetails = CarDetails;
        this.CarWork = WorkDone;
    }
}
