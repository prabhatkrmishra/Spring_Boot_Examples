package com.example.cnExpense.dal;

import com.example.cnExpense.entity.User;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class UserDalImpl implements UserDal {

    @Autowired
    EntityManager entityManager;

    @Override
    public User getUserById(Long id) {
        Session session = entityManager.unwrap(Session.class);
        User user = session.get(User.class, id);
        if (user == null) {
            throw new RuntimeException("User not found with id: " + id);
        }
        return user;
    }

    @Override
    public User saveUser(User user) {
        Session session = entityManager.unwrap(Session.class);

        // Check for duplicate username
        String hql = "FROM User WHERE username = :username";
        List<User> existingUsers = session.createQuery(hql, User.class)
                .setParameter("username", user.getUsername())
                .list();
        if (!existingUsers.isEmpty()) {
            throw new RuntimeException("Username already exists: " + user.getUsername());
        }

        session.saveOrUpdate(user);
        return user;
    }

    @Override
    public User updateUser(Long userId, User user) {
        Session session = entityManager.unwrap(Session.class);
        User existingUser = session.get(User.class, userId);
        if (existingUser == null) {
            throw new RuntimeException("User not found with id: " + userId);
        }

        if (user.getUsername() != null) existingUser.setUsername(user.getUsername());
        if (user.getNickname() != null) existingUser.setNickname(user.getNickname());
        if (user.getEmail() != null) existingUser.setEmail(user.getEmail());
        if (user.getAddress() != null) existingUser.setAddress(user.getAddress());

        session.saveOrUpdate(existingUser);
        return existingUser;
    }

    @Override
    public User setBudget(Long userId, double budget) {
        Session session = entityManager.unwrap(Session.class);
        User user = session.get(User.class, userId);
        if (user == null) {
            throw new RuntimeException("User not found with id: " + userId);
        }
        user.setBudget(budget);
        user.setIsbudgetSet(true);
        session.saveOrUpdate(user);
        return user;
    }

    @Override
    public double getTotalExpense(Long userId) {
        Session session = entityManager.unwrap(Session.class);
        String hql = "SELECT SUM(e.amount) FROM Expense e WHERE e.user.id = :userId";
        Query query = session.createQuery(hql);
        query.setParameter("userId", userId);
        Double result = (Double) query.getSingleResult();
        return result != null ? result : 0.0;
    }

    @Override
    public double getQuotation(Long userId) {
        Session session = entityManager.unwrap(Session.class);

        String incomeHql = "SELECT SUM(i.amount) FROM Income i WHERE i.user.id = :userId";
        Query incomeQuery = session.createQuery(incomeHql);
        incomeQuery.setParameter("userId", userId);
        Double totalIncome = (Double) incomeQuery.getSingleResult();
        totalIncome = totalIncome != null ? totalIncome : 0.0;

        String expenseHql = "SELECT SUM(e.amount) FROM Expense e WHERE e.user.id = :userId";
        Query expenseQuery = session.createQuery(expenseHql);
        expenseQuery.setParameter("userId", userId);
        Double totalExpense = (Double) expenseQuery.getSingleResult();
        totalExpense = totalExpense != null ? totalExpense : 0.0;

        return totalIncome - totalExpense;
    }

    @Override
    public double getAvgExpenseData(Long userId) {
        Session session = entityManager.unwrap(Session.class);
        String hql = "SELECT AVG(e.amount) FROM Expense e WHERE e.user.id = :userId";
        Query query = session.createQuery(hql);
        query.setParameter("userId", userId);
        Double result = (Double) query.getSingleResult();
        return result != null ? result : 0.0;
    }
}