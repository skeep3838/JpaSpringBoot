package com.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.Item;
import com.repository.ItemRepository;
import com.service.ItemService;
@Transactional
@Service
public class ItemServiceImpl implements ItemService {
	@Autowired
	ItemRepository dao;
	public void setDao(ItemRepository dao) {
		this.dao = dao;
	}
	@Override
	public List<Item> getAllItem() {
		return dao.findAll();
	}


}
