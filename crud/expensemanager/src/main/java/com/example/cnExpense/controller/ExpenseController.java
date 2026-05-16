package com.example.cnExpense.controller;

import com.example.cnExpense.entity.Expense;
import com.example.cnExpense.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/expense")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @PostMapping("/save/{userId}")
    public Expense saveExpense(@PathVariable Long userId, @RequestBody Expense expense) {
        
    }

    @GetMapping("/{expenseId}")
    public Expense getExpense(@PathVariable Long expenseId) {
        
    }
}
