package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.model.Itemline;
import com.model.Orders;


public interface ItemlineRepository extends JpaRepository<Itemline, Integer>{
	List<Itemline> findByOrderMap(Orders order);
	Itemline findBySeq(Integer seq);
}
