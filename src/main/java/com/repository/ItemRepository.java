package com.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import com.model.Item;

public interface ItemRepository extends JpaRepository<Item, Integer>{
	Item findByIid(Integer iid);
	
	@Procedure("priceRange")
	Integer priceRangeList(@Param("price") Integer price);
}

