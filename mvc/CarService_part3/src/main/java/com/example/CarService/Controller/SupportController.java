package com.example.CarService.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SupportController {

    @RequestMapping(value = "/support")
    public String getSupportPage(@RequestParam("id") String id,
                                 ModelMap modelMap) {

        modelMap.addAttribute("id", id);

        return "support";
    }
}