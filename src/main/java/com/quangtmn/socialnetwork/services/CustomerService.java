package com.quangtmn.socialnetwork.services;

import com.quangtmn.socialnetwork.entities.Customer;

public interface CustomerService {
    void insertCustomer(Customer customer);
    Customer getCustomerByPhone(String phone);
}
