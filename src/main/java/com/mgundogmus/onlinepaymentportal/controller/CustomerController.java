package com.mgundogmus.onlinepaymentportal.controller;

import com.mgundogmus.onlinepaymentportal.entity.Customer;
import com.mgundogmus.onlinepaymentportal.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "Yeni müşteri kaydı yapar", description = "Yeni bir müşteri kaydı oluşturur. Başarılı bir kayıt işlemi sonucunda 200, hatalı bir istek durumunda 400 hatası döner.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Müşteri başarıyla kaydedildi"),
            @ApiResponse(responseCode = "400", description = "Hatalı istek")
    })
    public Customer registerCustomer(@RequestBody Customer customer) {
        logger.info("Yeni müşteri kaydı yapıldı: {}", customer);
        return customerService.registerCustomer(customer);
    }
}