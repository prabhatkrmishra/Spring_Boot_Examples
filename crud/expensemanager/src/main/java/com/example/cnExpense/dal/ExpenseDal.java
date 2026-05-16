package com.example.cnExpense.dal;

import com.example.cnExpense.entity.Expense;

public interface ExpenseDal {

    Expense saveExpense(Long userId, Expense expense);

    Expense getExpenseById(Long expenseId);
}