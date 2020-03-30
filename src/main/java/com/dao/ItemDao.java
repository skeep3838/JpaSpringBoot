package com.dao;


import org.springframework.data.jpa.repository.JpaRepository;

import com.model.Item;

public interface ItemDao extends JpaRepository<Item, Integer>{
}
