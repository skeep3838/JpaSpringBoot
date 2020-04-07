package com.service;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.model.Item;

@Transactional
@Service
public interface ItemService {
	Page<Item> getAllItem(Integer page);
}
