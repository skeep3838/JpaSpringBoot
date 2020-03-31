package com.service;

import java.util.List;

import com.model.Itemline;
import com.model.Orders;

public interface ItemlineService {
	List<Itemline> getOrderDetailByOrder(Orders order);
	Itemline getItemlineBySeq(Integer seq);
	void addOrderDetail(Itemline orderList);
	Boolean deleteItem(Integer seq);
}
