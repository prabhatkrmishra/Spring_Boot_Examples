package com.example.testsite.webapp.controller;

import com.example.testsite.webapp.model.StudentUser;
import com.example.testsite.webapp.repository.StudentUserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class DetailsController {

    @Autowired
    StudentUserDAO studentUserDAO;

    @RequestMapping("/details")
    public String details(@RequestParam("id") String id, ModelMap modelMap){
        Optional<StudentUser> studentUser = studentUserDAO.getUser(Integer.parseInt(id));
        if(studentUser.isPresent()){
            modelMap.addAttribute("sid", studentUser.get().getId());
            modelMap.addAttribute("name", studentUser.get().getName());
            modelMap.addAttribute("gender", studentUser.get().getGender());
            modelMap.addAttribute("college", studentUser.get().getCollege());
            modelMap.addAttribute("location", studentUser.get().getLocation());
            return "details";
        }

        return "redirect:/";
    }
}
