package com.example.testsite.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
    @RequestMapping("/")
    public String home() {
        return "home";
    }

    @RequestMapping("/service")
    public String home(@RequestParam("id") String id, ModelMap modelMap) {
        modelMap.addAttribute("id", id);
        return "service";
    }
}
