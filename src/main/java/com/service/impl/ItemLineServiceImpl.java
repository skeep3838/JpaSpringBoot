package com.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.ItemlineDao;
import com.model.Itemline;
import com.service.ItemlineService;
@Transactional
@Service
public class ItemLineServiceImpl implements ItemlineService {
	ItemlineDao dao;
	@Autowired
	public void setDao(ItemlineDao dao) {
		this.dao = dao;
	}
//	@Override
//	public List<Itemline> getOrderDetailByOrder(int oid) {
//		return dao.findItemlineByOid(oid);
//	}
	@Override
	public void addOrderDetail(Itemline orderList) {
		dao.save(orderList);
		
	}

}
