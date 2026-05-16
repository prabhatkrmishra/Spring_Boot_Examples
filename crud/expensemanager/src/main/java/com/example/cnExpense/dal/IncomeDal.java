package com.example.cnExpense.dal;

import com.example.cnExpense.entity.Income;
import com.example.cnExpense.entity.User;

public interface IncomeDal {

    Income getIncomeById(Long incomeId);

    Income saveIncome(User user,Income income);

    Income updateIncome(Long incomeId, Income income);
}