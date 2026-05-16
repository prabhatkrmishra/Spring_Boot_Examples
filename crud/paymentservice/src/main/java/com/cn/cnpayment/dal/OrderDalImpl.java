 package com.cn.cnpayment.dal;

import com.cn.cnpayment.entity.Orders;
import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public class OrderDalImpl implements OrderDal
{

    @Autowired
    EntityManager entityManager;

    @Override
    public Orders getById(int id) {
        Session session = entityManager.unwrap(Session.class);
        Orders orders = session.get(Orders.class, id);
        return orders;
    }

    @Override
    public void save(Orders orders) {
        Session session = entityManager.unwrap(Session.class);
        session.save(orders);
    }

    @Override
    public void delete(int id) {
        Session session = entityManager.unwrap(Session.class);
        Orders orders = session.get(Orders.class, id);
        session.delete(orders);
    }

    @Override
    public List<Orders> getAllOrders() {
        Session session = entityManager.unwrap(Session.class);
        List<Orders> allOrders= session.createQuery(
                "SELECT p FROM Orders p", Orders.class).getResultList();
        return allOrders;
    }
}
