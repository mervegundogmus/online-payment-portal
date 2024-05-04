package com.mgundogmus.onlinepaymentportal.service;

import com.mgundogmus.onlinepaymentportal.entity.Payment;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface PaymentService {
    Payment makePayment(Payment payment);
    List<Payment> getPaymentsByCustomer(Long customerId);
    List<Payment> getPaymentsByDateRange(LocalDate startDate, LocalDate endDate);
}