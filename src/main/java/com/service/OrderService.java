package com.service;


import com.model.Customer;
import com.model.Orders;

public interface OrderService {
	Orders getOrderByCus(Customer customer);
	Orders getOrderByOid(Integer oid);
	Orders addOrder(Orders order);
}
