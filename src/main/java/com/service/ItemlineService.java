package com.service;

import java.util.List;

import com.model.Itemline;
import com.model.Orders;

public interface ItemlineService {
	List<Itemline> getOrderDetailByOrder(Orders order);
	void addOrderDetail(Itemline orderList);
}
