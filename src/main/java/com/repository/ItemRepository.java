package com.repository;


import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import com.model.Item;

public interface ItemRepository extends JpaRepository<Item, Integer>{
	Item findByIid(Integer iid);
	@Procedure(procedureName = "priceRange", outputParameterName = "countRow_out")
	Integer priceRangeEntity(Integer price_in);
//	@Procedure(procedureName = "price_Range_Item")
//	Object[] priceRangeItem(Integer price_in);
//	@Query(value = "EXEC price_Range_Item :price_in")
//	List<Item> priceRangeItem(@Param("price_in") Integer price_in);
}

