package com.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.model.Item;

@Transactional
@Service
public interface ItemService {
	Page<Item> getAllItem(Integer page);
	Item getItemById(Integer iid);
	Integer priceRangeList(Integer price);
}
