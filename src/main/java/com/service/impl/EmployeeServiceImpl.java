package com.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.Employee;
import com.repository.EmployeeRepository;
import com.service.EmployeeService;

@Transactional
@Service
public class EmployeeServiceImpl implements EmployeeService {
	EmployeeRepository dao;
	@Autowired
	public void setDao(EmployeeRepository dao) {
		this.dao = dao;
	}
	
	@Override
	public Employee findByEid(Integer eid) {
		return dao.findByEid(eid);
	}

	@Override
	public List<Employee> findAll() {
		return dao.findAll();
	}

}
