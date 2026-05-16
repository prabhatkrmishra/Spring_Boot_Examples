package com.example.cnExpense.dal;

import com.example.cnExpense.entity.User;

public interface UserDal {

    User getUserById(Long id);

    User saveUser(User user);

    User updateUser(Long userId, User user);

    User setBudget(Long userId, double budget);

    double getTotalExpense(Long userId);

    double getQuotation(Long userId);

    double getAvgExpenseData(Long userId);
}