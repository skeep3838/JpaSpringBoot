package com.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.Customer;
import com.model.Orders;
import com.repository.OrderRepository;
import com.service.OrderService;
@Transactional
@Service
public class OrderServiceImpl implements OrderService {
	OrderRepository dao;
	@Autowired
	public void setDao(OrderRepository dao) {
		this.dao = dao;
	}
	
	@Override
	public Orders getOrderByCus(Customer customer) {
		return dao.findByCustomer(customer);
	}
	
	@Override
	public Orders getOrderByOid(Integer oid) {
		return dao.findByOid(oid);
	}	
	
	@Override
	public Integer addOrder(Orders order) {
//		先新增一筆訂單
		dao.save(order);
//		取得最新的oid
		Integer oid = (Integer)dao.newOrderId();
		return oid;
	}

	@Override
	public void deleteOrder(Integer oid) {
		dao.deleteById(oid);		
	}

}
