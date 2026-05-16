package com.example.cnExpense.dal;

import com.example.cnExpense.entity.Expense;
import com.example.cnExpense.entity.User;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class ExpenseDalImpl implements ExpenseDal {

    @Autowired
    EntityManager entityManager;

    @Override
    public Expense saveExpense(Long userId, Expense expense) {
    
    }

    @Override
    public Expense getExpenseById(Long expenseId) {
     
    }
}


