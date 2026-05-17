package com.example.cnExpense.controller;

import com.example.cnExpense.entity.User;
import com.example.cnExpense.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/save")
    public User saveUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PutMapping("/update/{userId}")
    public User editUser(@PathVariable Long userId, @RequestBody User user) {
        return userService.editUser(userId, user);
    }

    @GetMapping("/{userId}/totalExpense")
    public double getTotalExpense(@PathVariable Long userId) {
        return userService.getTotalExpense(userId);
    }

    @GetMapping("/{userId}/quotation")
    public double getQuotation(@PathVariable Long userId) {
        return userService.getQuotation(userId);
    }

    @GetMapping("/{userId}/avgExpense")
    public double getAvgExpenseData(@PathVariable Long userId) {
        return userService.getAvgExpenseData(userId);
    }

    @PostMapping("/{userId}/setBudget")
    public User setBudget(@PathVariable Long userId, @RequestParam double budget) {
        return userService.setBudget(userId, budget);
    }
}