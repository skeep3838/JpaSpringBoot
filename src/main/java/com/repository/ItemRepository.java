package com.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;

import com.model.Item;

public interface ItemRepository extends JpaRepository<Item, Integer>{
	Item findByIid(Integer iid);
	@Procedure("priceRange")
	Integer priceRangeEntity(Integer price_in);
}

