package com.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.model.Employee;
import com.model.EmployeeOrdersJoinColumn;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	Employee findByEid(Integer eid);
//	@Query(value="Select e FROM employee e left join orders o on e.eid=o.eid", nativeQuery = true)
//	List<EmployeeOrdersJoinColumn> liftJoinColumn();

}
