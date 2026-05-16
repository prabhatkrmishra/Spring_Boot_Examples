package com.cn.cnpayment.service;

import com.cn.cnpayment.dal.OrderDal;
import com.cn.cnpayment.dal.PaymentDAL;
import com.cn.cnpayment.entity.Orders;
import com.cn.cnpayment.entity.Payment;
import com.cn.cnpayment.exception.NotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    OrderDal orderDAL;

    @Autowired
    PaymentDAL paymentDAL;

    @Transactional
    public Orders getOrderById(int id) {
        Orders orders =orderDAL.getById(id);
        if(orders ==null)
        {
            throw new NotFoundException("No order found with id:  "+id);
        }
        return orders;
    }

    @Transactional
    public List<Orders> getAllOrders() {

        List<Orders> orders = orderDAL.getAllOrders();
        if(orders.isEmpty())
        {
            throw new NotFoundException("No orders found.");
        }
        return orders;
    }

    @Transactional
    public void saveOrder(Orders newOrders) {

        Orders saveOrders = new Orders();
        saveOrders.setAmount(newOrders.getAmount());
        saveOrders.setName(newOrders.getName());
        saveOrders.setQuantity(newOrders.getQuantity());
        saveOrders.setCategory(newOrders.getCategory());
        List<Payment> paymentList = new ArrayList<>();
        for (Payment payment : newOrders.getPayments()) {
            Payment currentPayment = paymentDAL.getById(payment.getId());
            paymentList.add(currentPayment);
        }
        saveOrders.setPayments(paymentList);
        orderDAL.save(saveOrders);
    }

    @Transactional
    public void delete(int id) {
        orderDAL.delete(id);
    }


}
