package com.example.testsite.webapp.controller;

import com.example.testsite.webapp.services.UserMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Objects;

@Controller
public class SupportController {
    @Autowired
    UserMessageService userMessageService;

    @RequestMapping("/support")
    // /id="123"
    public String support(@RequestParam("id") String id, ModelMap modelMap) {
        String finalUserMessage = "NULL";

        if(Objects.equals(id, "null") || Objects.equals(id, "") || id == null) {
            modelMap.addAttribute("id", id);
            modelMap.addAttribute("message", finalUserMessage);
            return "support";
        }

        finalUserMessage = userMessageService.createMessage(Integer.parseInt(id));
        modelMap.addAttribute("id", id);
        modelMap.addAttribute("message", finalUserMessage);
        return "support";
    }
}
