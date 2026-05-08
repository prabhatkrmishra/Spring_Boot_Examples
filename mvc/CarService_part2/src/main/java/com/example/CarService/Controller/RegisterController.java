package com.example.CarService.Controller;

import com.example.CarService.domain.Car;
import com.example.CarService.service.Registration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RegisterController {

    @Autowired
    Registration registration;

    @RequestMapping("/register")
    public String getRegistrationPage(Model model) {

        model.addAttribute("car", registration.getNewCar());

        return "carregister";
    }

    @RequestMapping("/done")
    public String getResponsePage(@ModelAttribute("car") Car car){

        if(car.getRegisterationNumber() == null || car.getRegisterationNumber().isBlank()
                || car.getCarName() == null || car.getCarName().isBlank()
                || car.getCarDetails() == null || car.getCarDetails().isBlank()
                || car.getCarWork() == null || car.getCarWork().isBlank()){

            return "carregister";
        }

        Boolean result = registration.registerCar(
                car.getRegisterationNumber(),
                car.getCarName(),
                car.getCarDetails(),
                car.getCarWork()
        );

        if(result){
            return "success";
        }
        else{
            return "carregister";
        }
    }
}