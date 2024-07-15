package com.example.customermanagementsystem.sync;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.customermanagementsystem.product.Customer;
import com.example.customermanagementsystem.product.CustomerService;

@Service
public class SyncService {
    @Autowired
    private RemoteApiService remoteApiService;

    @Autowired
    private CustomerService customerService;

    public void syncCustomers() {
        List<Customer> remoteCustomers = remoteApiService.fetchCustomers();
        for (Customer remoteCustomer : remoteCustomers) {
            Customer existingCustomer = customerService.getCustomerById(remoteCustomer.getId());
            if (existingCustomer != null) {
                customerService.updateCustomer(existingCustomer.getId(), remoteCustomer);
            } else {
                customerService.createCustomer(remoteCustomer);
            }
        }
    }
}
