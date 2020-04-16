package com.service;

import java.util.List;

import com.model.Employee;
import com.model.EmployeeOrdersJoinColumn;

public interface EmployeeService {
	Employee findByEid(Integer eid);
	List<Employee> findAll();
	List<EmployeeOrdersJoinColumn> liftJoinColumn();
	List<EmployeeOrdersJoinColumn> innerJoinColumn();
}
