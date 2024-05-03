package com.mgundogmus.onlinepaymentportal.service.impl;

import com.mgundogmus.onlinepaymentportal.entity.Customer;
import com.mgundogmus.onlinepaymentportal.repository.CustomerRepository;
import com.mgundogmus.onlinepaymentportal.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Customer registerCustomer(Customer customer) {
        // E-posta kontrolü yapılır, daha önce kayıtlı mı diye
        if (customerRepository.findByEmail(customer.getEmail()) != null) {
            throw new IllegalArgumentException("Bu e-posta adresi zaten kayıtlı.");
        }

        // Müşterinin kaydedilmesi işlemi gerçekleştirilir
        return customerRepository.save(customer);
    }
}