package com.mgundogmus.onlinepaymentportal.controller;

import com.mgundogmus.onlinepaymentportal.entity.Payment;
import com.mgundogmus.onlinepaymentportal.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/new")
    public Payment makePayment(@RequestBody Payment payment) {
        return paymentService.makePayment(payment);
    }

    @GetMapping("/byCustomer/{customerId}")
    public List<Payment> getPaymentsByCustomer(@PathVariable Long customerId) {
        return paymentService.getPaymentsByCustomer(customerId);
    }

    @GetMapping("/byDate")
    public List<Payment> getPaymentsByDateRange(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate) {
        return paymentService.getPaymentsByDateRange(startDate, endDate);
    }
}