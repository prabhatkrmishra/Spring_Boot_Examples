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
        Session session = entityManager.unwrap(Session.class);
        User user = session.get(User.class, userId);

        if (user == null) {
            throw new RuntimeException("User not found with id: " + userId);
        }

        // Check budget if set
        if (user.isIsbudgetSet()) {
            String hql = "SELECT SUM(e.amount) FROM Expense e WHERE e.user.id = :userId";
            javax.persistence.Query query = session.createQuery(hql);
            query.setParameter("userId", userId);
            Double currentTotal = (Double) query.getSingleResult();
            currentTotal = currentTotal != null ? currentTotal : 0.0;

            if (currentTotal + expense.getAmount() > user.getBudget()) {
                throw new RuntimeException("Expense exceeds the set budget.");
            }
        }

        expense.setUser(user);
        user.getExpenses().add(expense);
        session.saveOrUpdate(expense);
        session.saveOrUpdate(user);
        return expense;
    }

    @Override
    public Expense getExpenseById(Long expenseId) {
        Session session = entityManager.unwrap(Session.class);
        Expense expense = session.get(Expense.class, expenseId);
        if (expense == null) {
            throw new RuntimeException("Expense not found with id: " + expenseId);
        }
        return expense;
    }
}