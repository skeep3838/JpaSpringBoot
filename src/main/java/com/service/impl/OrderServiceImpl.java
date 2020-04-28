package com.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.Customer;
import com.model.EmployeeOrdersJoinColumn;
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

	@Override
	public List<EmployeeOrdersJoinColumn> rightJoinColumn() {
		List<Orders> list = dao.findAll();
		List<EmployeeOrdersJoinColumn> list2 = new ArrayList<EmployeeOrdersJoinColumn>();
		Integer count=0;
		for(Orders o:list) {
			count+=1;
			EmployeeOrdersJoinColumn eojc = null;
			if(o.getEmployee() == null) {
				eojc = new EmployeeOrdersJoinColumn(count, null,
						null, o.getOid(),o.getCreateDate(),o.getCustomer().getCid());
			}else {
				eojc = new EmployeeOrdersJoinColumn(count, o.getEmployee().getEid(),
						o.getEmployee().getEname(), o.getOid(),o.getCreateDate(),o.getCustomer().getCid());	
			}
			list2.add(eojc);
		}
		for(EmployeeOrdersJoinColumn e:list2) {
			System.out.println(e.getEname());
		}
		return list2;
	}

	@Override
	public List<EmployeeOrdersJoinColumn> empNullJoinColumn() {
		List<Orders> list = dao.findByEmployeeIsNull();
		List<EmployeeOrdersJoinColumn> list2 = new ArrayList<EmployeeOrdersJoinColumn>();
		for(Orders o:list) {
			EmployeeOrdersJoinColumn eojc = new EmployeeOrdersJoinColumn(null, null, null,
					o.getOid(), o.getCreateDate(), o.getCustomer().getCid());
			list2.add(eojc);
		}
		return list2;
	}

}
