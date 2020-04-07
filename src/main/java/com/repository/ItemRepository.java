package com.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.model.Item;

public interface ItemRepository extends JpaRepository<Item, Integer>{
	Item findByIid(Integer iid);
}

