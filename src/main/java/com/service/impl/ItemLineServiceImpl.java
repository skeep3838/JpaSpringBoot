package com.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.Itemline;
import com.model.Orders;
import com.repository.ItemlineRepository;
import com.service.ItemlineService;
@Transactional
@Service
public class ItemLineServiceImpl implements ItemlineService {
	ItemlineRepository dao;
	@Autowired
	public void setDao(ItemlineRepository dao) {
		this.dao = dao;
	}
	@Override
	public List<Itemline> getOrderDetailByOrder(Orders order) {
		return dao.findByOrder(order);
//		return null;
	}
	@Override
	public void addOrderDetail(Itemline orderList) {
		dao.save(orderList);
		
	}
	@Override
	public Boolean deleteItem(Integer seq) {
		Itemline item = dao.findBySeq(seq);
		dao.delete(item);
		if(dao.findBySeq(seq)==null) {
			return true;
		}else {
			return false;
		}
	}
	@Override
	public Itemline getItemlineBySeq(Integer seq) {
		Itemline item = dao.findBySeq(seq);
		return item;
	}
	@Override
	public void updateItemQty(Integer seq, Integer qty) {
		dao.updateItemQty(seq, qty);
	}

}
