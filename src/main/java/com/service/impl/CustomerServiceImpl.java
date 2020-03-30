package com.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.CustomerDao;
import com.model.Customer;
import com.service.CustomerService;
@Transactional
@Service
public class CustomerServiceImpl implements CustomerService {
	CustomerDao dao;
	@Autowired
	public void setDao(CustomerDao dao) {
		this.dao = dao;
	}
	@Override
	public List<Customer> findAll() {
		 List<Customer> list = dao.findAll();
		return list;
	}
	@Override
	public Customer queryCustomerById(Integer id) {
		return dao.findByCid(id);
	}
	
	
}
