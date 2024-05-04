package com.mgundogmus.onlinepaymentportal.service.impl;

import com.mgundogmus.onlinepaymentportal.entity.Payment;
import com.mgundogmus.onlinepaymentportal.repository.PaymentRepository;
import com.mgundogmus.onlinepaymentportal.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public Payment makePayment(Payment payment) {
        // Ödeme miktarı 0'dan büyük olmalı
        if (payment.getAmount() <= 0) {
            throw new IllegalArgumentException("Ödeme miktarı 0'dan büyük olmalı.");
        }

        // Ödemenin yapılması işlemi gerçekleştirilir
        return paymentRepository.save(payment);
    }

    @Override
    public List<Payment> getPaymentsByCustomer(Long customerId) {
        // Müşteriye ait ödemelerin getirilmesi işlemleri burada gerçekleştirilir
        return paymentRepository.findByCustomerId(customerId);
    }

    @Override
    public List<Payment> getPaymentsByDateRange(LocalDate startDate, LocalDate endDate) {
        // Belirli bir tarih aralığındaki ödemelerin getirilmesi işlemleri burada gerçekleştirilir
        return paymentRepository.findByPaymentDateBetween(startDate, endDate);
    }
}