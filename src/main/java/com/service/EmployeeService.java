package com.service;

import java.util.List;
import com.model.Employee;
import com.model.EmployeeOrdersJoinColumn;

public interface EmployeeService {
	Employee findByEid(Integer eid);
	List<Employee> findAll();
	List<EmployeeOrdersJoinColumn> leftJoinColumn();
	List<EmployeeOrdersJoinColumn> innerJoinColumn();
	List<EmployeeOrdersJoinColumn> leftJoinColumn2();
	List<EmployeeOrdersJoinColumn> innerJoinColumn2();
}
