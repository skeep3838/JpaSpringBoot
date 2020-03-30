package com.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.OrderDao;
import com.model.Customer;
import com.model.Orders;
import com.service.OrderService;
@Transactional
@Service
public class OrderServiceImpl implements OrderService {
	OrderDao dao;
	@Autowired
	public void setDao(OrderDao dao) {
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
	public Orders addOrder(Orders order) {
//		先新增一筆訂單
		dao.save(order);
//		取得最新的oid
		Integer oid = (Integer)dao.newOrderId();
		Orders newOrder = dao.findByOid(oid);
		return newOrder;
	}

}
