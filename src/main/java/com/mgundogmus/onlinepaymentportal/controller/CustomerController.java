package com.mgundogmus.onlinepaymentportal.controller;

import com.mgundogmus.onlinepaymentportal.entity.Customer;
import com.mgundogmus.onlinepaymentportal.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private CustomerService customerService;

    @PostMapping("/register")
    public Customer registerCustomer(@RequestBody Customer customer) {
        logger.info("Register {}", customer);
        return customerService.registerCustomer(customer);
    }
    // Diğer endpoint'ler buraya eklenebilir
}