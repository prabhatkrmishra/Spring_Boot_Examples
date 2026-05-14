package com.cn.cnpayment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.cn.cnpayment.entity.Payment;
import com.cn.cnpayment.service.PaymentService;

import java.util.List;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @GetMapping("/id/{id}")
    public Payment getPaymentById(@PathVariable int id) {
        return paymentService.getPaymentById(id);
    }

    @GetMapping("/allPayments")
    public List<Payment> getAllPayments() {
        return paymentService.getAllPayments();
    }

    @GetMapping("/paymentType/{paymentType}")
    public List<Payment> getPaymentByPaymentType(@PathVariable String paymentType) {
        return paymentService.getPaymentByPaymentType(paymentType);
    }

    @GetMapping("/description/{keyword}")
    public List<Payment> getPaymentByDescriptionKeyword(@PathVariable String keyword) {
        return paymentService.getPaymentByDescriptionKeyword(keyword);
    }

    @PostMapping("/save")
    public void addPayment(@RequestBody Payment payment) {
        paymentService.addPayment(payment);
    }

    @DeleteMapping("/delete/id/{id}")
    public void deletePayment(@PathVariable int id) {
        paymentService.delete(id);
    }

    @PutMapping("/update")
    public void updatePayment(@RequestBody Payment payment) {
        paymentService.update(payment);
    }

    @PutMapping("/update/{id}/description/{description}")
    public void updateDescription(@PathVariable("id") int id, @PathVariable("description") String description) {
        paymentService.updateDescription(id, description);
    }
}