package com.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.Employee;
import com.model.EmployeeOrdersJoinColumn;
import com.model.Orders;
import com.repository.EmployeeRepository;
import com.repository.StoreProcedureDao;
import com.service.EmployeeService;

@Transactional
@Service
public class EmployeeServiceImpl implements EmployeeService {
	EmployeeRepository empDao;

	@Autowired
	public void setEmpDao(EmployeeRepository empDao) {
		this.empDao = empDao;
	}

	StoreProcedureDao spDao;

	@Autowired
	public void setSpDao(StoreProcedureDao spDao) {
		this.spDao = spDao;
	}

	@Override
	public Employee findByEid(Integer eid) {
		return empDao.findByEid(eid);
	}

	@Override
	public List<Employee> findAll() {
		return empDao.findAll();
	}

	@Override
	public List<EmployeeOrdersJoinColumn> leftJoinColumn() {
//		 TypedQuery<EmployeeOrdersJoinColumn> query =
//			      em.createNamedQuery("employee.left", EmployeeOrdersJoinColumn.class);
//		 List<EmployeeOrdersJoinColumn> list = query.getResultList();
		List<Employee> list = empDao.findAll();
		List<EmployeeOrdersJoinColumn> list2 = new ArrayList<EmployeeOrdersJoinColumn>();
		Integer count = 0;
		for (Employee e : list) {
			if (e.getOrderList().isEmpty()) {
				count += 1;
				EmployeeOrdersJoinColumn eojc = new EmployeeOrdersJoinColumn(count, e.getEid(), e.getEname(), null,
						null, null);
				list2.add(eojc);
			} else {
				for (Orders o : e.getOrderList()) {
					count += 1;
					EmployeeOrdersJoinColumn eojc = new EmployeeOrdersJoinColumn(count, e.getEid(), e.getEname(),
							o.getOid(), o.getCreateDate(), o.getCustomer().getCid());
					list2.add(eojc);
				}
			}
		}
		return list2;
	}

	@Override
	public List<EmployeeOrdersJoinColumn> innerJoinColumn() {
		List<Employee> list = empDao.findAll();
		List<EmployeeOrdersJoinColumn> list2 = new ArrayList<EmployeeOrdersJoinColumn>();
		Integer count = 0;
		for (Employee e : list) {
			if (!e.getOrderList().isEmpty()) {
				for (Orders o : e.getOrderList()) {
					count += 1;
					EmployeeOrdersJoinColumn eojc = new EmployeeOrdersJoinColumn(count, e.getEid(), e.getEname(),
							o.getOid(), o.getCreateDate(), o.getCustomer().getCid());
					list2.add(eojc);
				}
			}

		}
		return list2;
	}

	@Override
	public List<EmployeeOrdersJoinColumn> leftJoinColumn2() {
		return spDao.leftJoinColumn2();
	}

	@Override
	public List<EmployeeOrdersJoinColumn> innerJoinColumn2() {
		return spDao.innerJoinColumn2();
	}

}
