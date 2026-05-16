package com.cn.cnpayment.controller;

import com.cn.cnpayment.entity.Orders;
import com.cn.cnpayment.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping("/save")
    public void saveOrder(@RequestBody Orders orders){
        orderService.saveOrder(orders);
    }

    @GetMapping("/id/{id}")
    public Orders getOrder(@PathVariable int id){
        return orderService.getOrderById(id);
    }

    @DeleteMapping("/id/{id}")
    public void deleteOrder(@PathVariable int id){
        orderService.delete(id);
    }

    @GetMapping("/allOrders")
    public List<Orders> getAllOrders(){
         return orderService.getAllOrders();
    }
}
