package com.service;

import java.util.List;

import com.model.Customer;

public interface CustomerService {
	List<Customer> findAll();
	Customer queryCustomerById(Integer id);
}
