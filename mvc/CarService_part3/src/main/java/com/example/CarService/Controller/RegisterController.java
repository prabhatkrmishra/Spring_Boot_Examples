package com.example.CarService.Controller;

import com.example.CarService.domain.Car;
import com.example.CarService.domain.Vehicle;
import com.example.CarService.service.Registration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RegisterController {

    @Autowired
    Registration registration;

    @RequestMapping("/register")
    public String getRegistrationPage(Model carModel){

        Vehicle vehicle = registration.getNewCar();

        carModel.addAttribute("vehicle", vehicle);

        return "carregister";
    }

    @RequestMapping("/done")
    public ModelAndView getResponsePage(@ModelAttribute("vehicle") Car car){

        ModelAndView mav = new ModelAndView();

        int id = registration.registerCar(
                car.getRegisterationNumber(),
                car.getCarName(),
                car.getCarDetails(),
                car.getCarWork()
        );

        if(id != -1){

            mav.setViewName("redirect:success?id=" + id);

            return mav;
        }

        mav.setViewName("carregister");

        return mav;
    }

    @RequestMapping(value="/success")
    public String getSuccessPage(@RequestParam("id") String id,
                                 Model model) {

        model.addAttribute("id", id);

        return "success";
    }
}