package com.service;

import java.util.List;

import com.model.Employee;

public interface EmployeeService {
	Employee findByEid(Integer eid);
	List<Employee> findAll();
}
