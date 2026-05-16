package com.example.cnExpense.service;

import com.example.cnExpense.dal.ExpenseDal;
import com.example.cnExpense.entity.Expense;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseDal expenseDal;

    @Transactional
    public Expense saveExpense(Long userId, Expense expense) {
      
    }

    @Transactional
    public Expense getExpenseById(Long expenseId) {
        
    }
}