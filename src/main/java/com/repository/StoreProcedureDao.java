package com.repository;

import java.util.List;

import com.model.Item;

public interface StoreProcedureDao {
	List<Item> priceRangeItem(Integer price);
}
