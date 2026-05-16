package com.example.cnExpense.dal;

import com.example.cnExpense.entity.Expense;
import com.example.cnExpense.entity.Income;
import com.example.cnExpense.entity.User;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class UserDalImpl implements UserDal {

    @Autowired
    EntityManager entityManager;

    @Override
    public User getUserById(Long id) {
      
    }

    @Override
    public User saveUser(User user) {
     
    }

    @Override
    public User updateUser(Long userId, User user) {
    
    }

    @Override
    public User setBudget(Long userId, double budget) {
        
    }

    @Override
    public double getTotalExpense(Long userId) {
      
    }

    @Override
    public double getQuotation(Long userId) {
       
    }

    @Override
    public double getAvgExpenseData(Long userId) {
    
    }
}


