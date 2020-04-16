package com.service;


import java.util.List;

import com.model.Customer;
import com.model.EmployeeOrdersJoinColumn;
import com.model.Orders;

public interface OrderService {
	Orders getOrderByCus(Customer customer);
	Orders getOrderByOid(Integer oid);
	Integer addOrder(Orders order);
	void deleteOrder(Integer oid);
	List<EmployeeOrdersJoinColumn> rightJoinColumn();
	List<EmployeeOrdersJoinColumn> empNullJoinColumn();
}
