package com.repository;

import java.util.List;

import com.model.Employee;
import com.model.EmployeeOrdersJoinColumn;
import com.model.Item;

public interface StoreProcedureDao {
	List<Item> priceRangeItem(Integer price);
	List<EmployeeOrdersJoinColumn> leftJoinColumn2();
	List<EmployeeOrdersJoinColumn> innerJoinColumn2();
}
