package com.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.model.Itemline;
import com.model.Orders;


public interface ItemlineDao extends JpaRepository<Itemline, Integer>{
	List<Itemline> findByOrderMap(Orders order);
	Itemline findBySeq(Integer seq);
}
