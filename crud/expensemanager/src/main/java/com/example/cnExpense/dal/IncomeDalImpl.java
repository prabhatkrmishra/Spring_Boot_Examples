package com.example.cnExpense.dal;

import com.example.cnExpense.entity.Income;
import com.example.cnExpense.entity.User;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class IncomeDalImpl implements IncomeDal {

    @Autowired
    EntityManager entityManager;

    @Override
    public Income getIncomeById(Long incomeId) {
        Session session = entityManager.unwrap(Session.class);
        Income income = session.get(Income.class, incomeId);
        if (income == null) {
            throw new RuntimeException("Income not found with id: " + incomeId);
        }
        return income;
    }

    @Override
    public Income saveIncome(User user, Income newIncome) {
        Session session = entityManager.unwrap(Session.class);
        newIncome.setUser(user);
        user.getIncomes().add(newIncome);
        session.saveOrUpdate(newIncome);
        session.saveOrUpdate(user);
        return newIncome;
    }

    @Override
    public Income updateIncome(Long incomeId, Income income) {
        Session session = entityManager.unwrap(Session.class);
        Income existingIncome = session.get(Income.class, incomeId);
        if (existingIncome == null) {
            throw new RuntimeException("Income not found with id: " + incomeId);
        }

        if (income.getAmount() != 0) existingIncome.setAmount(income.getAmount());
        if (income.getDate() != null) existingIncome.setDate(income.getDate());
        if (income.getDescription() != null) existingIncome.setDescription(income.getDescription());
        if (income.getIncomeType() != null) existingIncome.setIncomeType(income.getIncomeType());

        session.saveOrUpdate(existingIncome);
        return existingIncome;
    }
}