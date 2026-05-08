package com.example.CarService.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class attendentController {

    @GetMapping("/attendent/{attendentId}")
    public String getAttendent(@PathVariable String attendentId,
                               ModelMap modelMap){

        modelMap.addAttribute("name", "TEST 123");

        modelMap.addAttribute("speciality",
                "Engine,BodyShop");

        modelMap.addAttribute("id", attendentId);

        return "attendent";
    }
}