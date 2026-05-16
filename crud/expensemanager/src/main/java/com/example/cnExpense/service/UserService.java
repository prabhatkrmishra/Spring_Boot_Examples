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
    
    }

    @Transactional
    public User saveUser(User user) {
        
    }

    @Transactional
    public User editUser(Long userId, User user) {
        
    }

    @Transactional
    public User setBudget(Long userId, double budget) {
        
    }

    @Transactional
    public double getTotalExpense(Long userId) {
        
    }

    @Transactional
    public double getQuotation(Long userId) {
        
    }

    @Transactional
    public double getAvgExpenseData(Long userId) {

    }
}