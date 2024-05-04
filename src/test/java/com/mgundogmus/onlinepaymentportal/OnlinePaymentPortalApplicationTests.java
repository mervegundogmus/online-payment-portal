package com.mgundogmus.onlinepaymentportal;

import org.junit.jupiter.api.Test;

import com.mgundogmus.onlinepaymentportal.controller.CustomerController;
import com.mgundogmus.onlinepaymentportal.controller.PaymentController;
import com.mgundogmus.onlinepaymentportal.entity.Customer;
import com.mgundogmus.onlinepaymentportal.entity.Payment;
import com.mgundogmus.onlinepaymentportal.service.CustomerService;
import com.mgundogmus.onlinepaymentportal.service.PaymentService;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class OnlinePaymentPortalApplicationTests {

    @Mock
    private CustomerService customerService;

    @Mock
    private PaymentService paymentService;

    @InjectMocks
    private CustomerController customerController;

    @InjectMocks
    private PaymentController paymentController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testRegisterCustomer() {
        Customer customer = new Customer();
        customer.setEmail("test@example.com");
        customer.setFirstName("John");
        customer.setLastName("Doe");

        when(customerService.registerCustomer(customer)).thenReturn(customer);

        Customer registeredCustomer = customerController.registerCustomer(customer);

        assertEquals(customer, registeredCustomer);
    }

    @Test
    public void testMakePayment() {
        Payment payment = new Payment();
        payment.setAmount(100.00);
        payment.setPaymentDate(LocalDate.now());

        when(paymentService.makePayment(payment)).thenReturn(payment);

        Payment returnedPayment = paymentController.makePayment(payment);

        assertNotNull(returnedPayment);
        assertEquals(payment.getAmount(), returnedPayment.getAmount());
        assertEquals(payment.getPaymentDate(), returnedPayment.getPaymentDate());
    }

    @Test
    public void testGetPaymentsByCustomer() {
        Long customerId = 1L;
        List<Payment> payments = Collections.singletonList(new Payment());

        when(paymentService.getPaymentsByCustomer(customerId)).thenReturn(payments);

        ResponseEntity<List<Payment>> responseEntity = paymentController.getPaymentsByCustomer(customerId);

        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals(payments, responseEntity.getBody());
    }

    @Test
    public void testGetPaymentsByDateRange() {
        LocalDate startDate = LocalDate.of(2022, 1, 1);
        LocalDate endDate = LocalDate.of(2022, 12, 31);
        List<Payment> payments = Collections.singletonList(new Payment());

        when(paymentService.getPaymentsByDateRange(startDate, endDate)).thenReturn(payments);

        ResponseEntity<List<Payment>> responseEntity = paymentController.getPaymentsByDateRange(startDate, endDate);

        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals(payments, responseEntity.getBody());
    }
}
