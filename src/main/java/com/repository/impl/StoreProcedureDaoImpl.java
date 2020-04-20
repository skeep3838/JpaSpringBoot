package com.repository.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.StoredProcedureQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.model.Item;
import com.repository.StoreProcedureDao;
@Repository
public class StoreProcedureDaoImpl implements StoreProcedureDao {

	EntityManager em;
	@Autowired
	public void setFactory(EntityManager em) {
		this.em = em;
	}
	@Override
	public List<Item> priceRangeItem(Integer price) {
		StoredProcedureQuery storedProcedure = 
				em.createNamedStoredProcedureQuery("price_Range_Item")
				.setParameter("price_in", price);
		@SuppressWarnings("unchecked")
		List<Item> list = (ArrayList<Item>)storedProcedure.getResultList();
		return list;
	}

}
