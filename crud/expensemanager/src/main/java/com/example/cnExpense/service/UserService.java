package com.example.cnExpense.service;

import com.example.cnExpense.dal.UserDal;
import com.example.cnExpense.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserService {

    @Autowired
    private UserDal userDal;

    @Transactional
    public User getUserById(Long id) {
        return userDal.getUserById(id);
    }

    @Transactional
    public User saveUser(User user) {
        return userDal.saveUser(user);
    }

    @Transactional
    public User editUser(Long userId, User user) {
        return userDal.updateUser(userId, user);
    }

    @Transactional
    public User setBudget(Long userId, double budget) {
        return userDal.setBudget(userId, budget);
    }

    @Transactional
    public double getTotalExpense(Long userId) {
        return userDal.getTotalExpense(userId);
    }

    @Transactional
    public double getQuotation(Long userId) {
        return userDal.getQuotation(userId);
    }

    @Transactional
    public double getAvgExpenseData(Long userId) {
        return userDal.getAvgExpenseData(userId);
    }
}