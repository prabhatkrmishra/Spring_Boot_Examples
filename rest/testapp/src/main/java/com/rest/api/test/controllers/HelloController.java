package com.rest.api.test.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {

    // /hello/human/14
    @GetMapping("/human/{code}")
    public String human(@PathVariable Integer code) {
        // returning html doc
        return "<b>Hello World, from a HUMAN!</b> " + "<u>" + (code * code) + "</u>";
    }

    // /hello/robot
    @GetMapping("/robot")
    public String robot() {
        return "Hello World, from a ROBOT!";
    }
}
