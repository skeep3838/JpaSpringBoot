package com.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.model.Customer;

public interface CustomerDao extends JpaRepository<Customer, Integer> {
	Customer findByCid(Integer cid);
}
