package com.quangtmn.socialnetwork.services.impl;

import com.quangtmn.socialnetwork.entities.Customer;
import com.quangtmn.socialnetwork.exceptions.KeyDuplicateException;
import com.quangtmn.socialnetwork.messages.Message;
import com.quangtmn.socialnetwork.repositories.CustomerRepository;
import com.quangtmn.socialnetwork.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImp implements CustomerService {
    @Autowired
    CustomerRepository customerRepository;
    @Override
    public void insertCustomer(Customer customer) {
        Customer checkCustomer = customerRepository.findCustomerByPhone(customer.getPhone());
        if (checkCustomer != null) {
            throw new KeyDuplicateException(Message.CUSTOMER_EXIST);
        } else {
            customerRepository.save(customer);
        }
    }

    @Override
    public Customer getCustomerByPhone(String phone) {
        return customerRepository.findCustomerByPhone(phone);
    }

}
