package com.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>,JpaSpecificationExecutor<Employee> {
	Employee findByEid(Integer eid);
//	@Query(value="Select e FROM employee e left join orders o on e.eid=o.eid", nativeQuery = true)
//	List<EmployeeOrdersJoinColumn> liftJoinColumn();

}
