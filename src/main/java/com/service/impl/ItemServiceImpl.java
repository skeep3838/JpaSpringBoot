package com.service.impl;


import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
	public Page<Item> getAllItem(Integer page) {
//		Sort sort = Sort.by("iid").ascending();
//		Sort sort = Sort.by("iid").descending();
		Pageable pageable = PageRequest.of(page, 5, Sort.Direction.DESC,"iid");
		Page<Item> item = dao.findAll(pageable);
		
//		System.out.println("全部筆數: "+item.getTotalElements());
//		System.out.println("總頁數: "+item.getTotalPages());
//		for(Item i:item.getContent()) {
//			System.out.println(i.getIname());
//		}
		return item;
	}
	@Override
	public Item getItemById(Integer iid) {		
		return dao.findByIid(iid);
	}
	@Override
	public Integer priceRangeList(Integer price) {
		
		return dao.priceRangeList(price);
	}


}
