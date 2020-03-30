package com.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.model.Customer;
import com.model.Orders;

@Repository
public interface OrderDao extends JpaRepository<Orders, Integer>{
	Orders findByCustomer(Customer customer);
	Orders findByOid(Integer oid);
//	透過NamedQuery取得最新的oid
	Integer newOrderId();
}
