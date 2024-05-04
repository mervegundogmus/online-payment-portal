package com.mgundogmus.onlinepaymentportal.controller;

import com.mgundogmus.onlinepaymentportal.entity.Payment;
import com.mgundogmus.onlinepaymentportal.service.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    private final Logger logger = LoggerFactory.getLogger(PaymentController.class);
    @Autowired
    private PaymentService paymentService;

    @PostMapping("/new")
    @Operation(summary = "Yeni ödeme yapar", description = "Yeni bir ödeme talebi alır ve işler. Başarılı bir ödeme işlemi sonucunda 200, hatalı bir istek durumunda 400 hatası döner.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ödeme başarıyla gerçekleştirildi"),
            @ApiResponse(responseCode = "400", description = "Hatalı istek")
    })
    public Payment makePayment(@RequestBody Payment payment) {
        logger.info("Yeni ödeme talebi alındı: {}", payment);
        return paymentService.makePayment(payment);
    }

    @GetMapping("/byCustomer/{customerId}")
    @Operation(summary = "Müşteriye ait ödemeleri getirir", description = "Belirtilen müşteriye ait ödemeleri getirir. Ödeme bulunamazsa 404 hatası döner.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ödemeler bulundu ve listelendi"),
            @ApiResponse(responseCode = "404", description = "Müşteriye ait ödeme bulunamadı")
    })
    public ResponseEntity<List<Payment>> getPaymentsByCustomer(@PathVariable Long customerId) {
        logger.info("Müşteriye ait ödemeler getiriliyor, customerId: {}", customerId);
        List<Payment> payments = paymentService.getPaymentsByCustomer(customerId);
        if (payments.isEmpty()) {
            logger.info("Müşteriye ait ödeme bulunamadı, customerId: {}", customerId);
            return ResponseEntity.notFound().build(); // 404 - Müşteriye ait ödeme bulunamadı
        }
        return ResponseEntity.ok(payments); // 200 - Ödemeler bulundu ve listelendi
    }

    @GetMapping("/byDate")
    @Operation(summary = "Belirli bir tarih aralığına göre ödemeleri getirir", description = "Belirtilen tarih aralığına göre ödemeleri getirir. Ödeme bulunamazsa 404 hatası döner.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ödemeler bulundu ve listelendi"),
            @ApiResponse(responseCode = "404", description = "Belirtilen tarih aralığına ait ödeme bulunamadı")
    })
    public ResponseEntity<List<Payment>> getPaymentsByDateRange(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate) {
        logger.info("Tarih aralığına göre ödemeler getiriliyor, startDate: {}, endDate: {}", startDate, endDate);
        List<Payment> payments = paymentService.getPaymentsByDateRange(startDate, endDate);
        if (payments.isEmpty()) {
            logger.info("Belirtilen tarih aralığına ait ödeme bulunamadı, startDate: {}, endDate: {}", startDate, endDate);
            return ResponseEntity.notFound().build(); // 404 - Belirtilen tarih aralığına ait ödeme bulunamadı
        }
        return ResponseEntity.ok(payments); // 200 - Ödemeler bulundu ve listelendi
    }
}