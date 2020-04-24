package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.model.Customer;
import com.model.Employee;
import com.model.Orders;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Integer>, JpaSpecificationExecutor<Orders>{
	Orders findByCustomer(Customer customer);
	Orders findByOid(Integer oid);
//	透過NamedQuery取得最新的oid
	@Query(value="SELECT MAX(oid) FROM Orders")
	Integer newOrderId();
	List<Orders> findByEmployeeIsNull();
}
