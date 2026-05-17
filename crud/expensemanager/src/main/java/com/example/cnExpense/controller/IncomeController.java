package com.example.cnExpense.controller;

import com.example.cnExpense.entity.Income;
import com.example.cnExpense.service.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/income")
public class IncomeController {

    @Autowired
    private IncomeService incomeService;

    @PostMapping("/save/{userId}")
    public Income saveIncome(@PathVariable Long userId, @RequestBody Income income) {
        return incomeService.saveIncome(userId, income);
    }

    @GetMapping("/{incomeId}")
    public Income getIncomeById(@PathVariable Long incomeId) {
        return incomeService.getIncomeById(incomeId);
    }

    @PutMapping("/update/{incomeId}")
    public Income editIncome(@PathVariable Long incomeId, @RequestBody Income income) {
        return incomeService.editIncome(incomeId, income);
    }
}