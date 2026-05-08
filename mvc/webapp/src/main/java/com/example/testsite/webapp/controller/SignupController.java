package com.example.testsite.webapp.controller;

import com.example.testsite.webapp.model.StudentUser;
import com.example.testsite.webapp.model.User;
import com.example.testsite.webapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SignupController {
    @Autowired
    UserService userService;

    @RequestMapping("/signup")
    public String signup(Model uiModel) {
        User user = userService.getUser();
        uiModel.addAttribute("user", user);
        return "signup";
    }

    @RequestMapping("/register")
    public String createUser(@ModelAttribute(value = "user") StudentUser user) {

        int id = userService.signUp(user.getName(), user.getGender(), user.getLocation(), user.getCollege());
        if (id > -1) {
            return "redirect:/success?id=" + id;
        }

        return "signup";
    }

    @RequestMapping("/success")
    public String showRegistered(@RequestParam("id") String id, ModelMap modelMap) {
        modelMap.addAttribute("id", Integer.parseInt(id));
        return "success";
    }
}