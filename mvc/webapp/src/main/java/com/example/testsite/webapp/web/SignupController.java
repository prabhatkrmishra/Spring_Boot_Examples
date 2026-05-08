package com.example.testsite.webapp.web;

import com.example.testsite.webapp.domain.StudentUser;
import com.example.testsite.webapp.domain.User;
import com.example.testsite.webapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @RequestMapping("/registerUser")
    public String createUser(@ModelAttribute(value = "user") StudentUser user) {
        if (userService.signUp(user.getName(), user.getGender(), user.getLocation(), user.getCollege()))
            return "registrationSuccess";

        return "signup";
    }
}
