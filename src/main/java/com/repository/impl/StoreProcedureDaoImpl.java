package com.repository.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.model.Employee;
import com.model.EmployeeOrdersJoinColumn;
import com.model.Item;
import com.model.Orders;
import com.repository.StoreProcedureDao;
@Repository
public class StoreProcedureDaoImpl implements StoreProcedureDao {

	EntityManager em;
	@Autowired
	public void setFactory(EntityManager em) {
		this.em = em;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Item> priceRangeItem(Integer price) {
		StoredProcedureQuery storedProcedure = 
				em.createNamedStoredProcedureQuery("price_Range_Item")
				.setParameter("price_in", price);
		storedProcedure.execute();
		List<Item> list = (ArrayList<Item>)storedProcedure.getResultList();
		return list;
	}
	@Override
	public List<EmployeeOrdersJoinColumn> leftJoinColumn2() {
		/** create  a Criteria Builder **/
	    CriteriaBuilder builder = em.getCriteriaBuilder();
	    /** create a CriteriaQuery which returns EmployeeOrdersJoinColumn Objects **/
	    CriteriaQuery<EmployeeOrdersJoinColumn> cq = builder.createQuery(EmployeeOrdersJoinColumn.class);
	    /** fetch the Employee Entity **/
	    Root<Employee> empRoot = cq.from(Employee.class);
	    /** select Employee left join Orders**/
	    Join<Orders, Employee> join = empRoot.join("orderList", JoinType.LEFT);
	    /** 選擇要select的欄位  ,join.get("oid"), join.get("createdate") ,join.get("cid")**/
	    cq.multiselect(empRoot.get("eid"), empRoot.get("ename"), join.get("oid") ,join.get("createdate") ,join.get("customer").get("cid"));
	    /** fetch the Employee result **/
	    List<EmployeeOrdersJoinColumn> result = em.createQuery(cq).getResultList();
	    // 塞入seq的值
	    int seq=1;
	    for(EmployeeOrdersJoinColumn e:result) {
	    	e.setSeq(seq);
	    	seq++;
	    }
		return result;
	}
	@Override
	public List<EmployeeOrdersJoinColumn> innerJoinColumn2() {
	    CriteriaBuilder builder = em.getCriteriaBuilder();
	    CriteriaQuery<EmployeeOrdersJoinColumn> cq = builder.createQuery(EmployeeOrdersJoinColumn.class);
	    Root<Employee> empRoot = cq.from(Employee.class);
	    Join<Orders, Employee> join = empRoot.join("orderList", JoinType.INNER);
//	    , join.get("oid") ,join.get("createdate") ,join.get("customer").get("cid")
	    cq.multiselect(empRoot.get("eid"), empRoot.get("ename"), join.get("oid") ,join.get("createdate") ,join.get("customer").get("cid"));
	    List<EmployeeOrdersJoinColumn> result = (List<EmployeeOrdersJoinColumn>)em.createQuery(cq).getResultList();
	    int seq=1;
	    for(EmployeeOrdersJoinColumn e:result) {
	    	e.setSeq(seq);
	    	seq++;
	    }
		return result;
	}
	
	
	

}
