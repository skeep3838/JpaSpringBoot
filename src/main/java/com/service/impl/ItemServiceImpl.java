package com.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.model.Item;
import com.repository.ItemRepository;
import com.repository.StoreProcedureDao;
import com.service.ItemService;
@Transactional
@Service
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	StoreProcedureDao storeProcedureDao;
	public void setStoreProcedureDao(StoreProcedureDao storeProcedureDao) {
		this.storeProcedureDao = storeProcedureDao;
	}
	
	@Autowired
	ItemRepository itemRepository;
	public void setItemRepository(ItemRepository dao) {
		this.itemRepository = dao;
	}
	@Override
	public Page<Item> getAllItem(Integer page,String sortItem) {
//		Sort sort = Sort.by("iid").ascending();
//		Sort sort = Sort.by("iid").descending();
		Pageable pageable = PageRequest.of(page, 5, Sort.Direction.ASC,sortItem);
		Page<Item> item = itemRepository.findAll(pageable);
		
//		System.out.println("全部筆數: "+item.getTotalElements());
//		System.out.println("總頁數: "+item.getTotalPages());
//		for(Item i:item.getContent()) {
//			System.out.println(i.getIname());
//		}
		return item;
	}
	@Override
	public Item getItemById(Integer iid) {		
		return itemRepository.findByIid(iid);
	}
	@Override
	public Integer priceRangeEntity(Integer price_in) {
		return itemRepository.priceRangeEntity(price_in);
	}
	@Override
	public List<Item> priceRangeItem(Integer price) {
		List<Item> list = storeProcedureDao.priceRangeItem(price);
		return list;
	}



}
