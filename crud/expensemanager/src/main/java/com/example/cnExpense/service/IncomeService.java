package com.example.cnExpense.service;

import com.example.cnExpense.dal.IncomeDal;
import com.example.cnExpense.dal.UserDal;
import com.example.cnExpense.entity.Income;
import com.example.cnExpense.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class IncomeService {

    @Autowired
    private IncomeDal incomeDal;

    @Autowired
    private UserDal userDal;

    @Transactional
    public Income getIncomeById(Long incomeId) {
        return incomeDal.getIncomeById(incomeId);
    }

    @Transactional
    public Income saveIncome(Long userId, Income income) {
        User user = userDal.getUserById(userId);
        return incomeDal.saveIncome(user, income);
    }

    @Transactional
    public Income editIncome(Long incomeId, Income income) {
        return incomeDal.updateIncome(incomeId, income);
    }
}