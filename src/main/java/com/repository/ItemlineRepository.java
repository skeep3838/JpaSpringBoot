package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;

import com.model.Itemline;
import com.model.Orders;


public interface ItemlineRepository extends JpaRepository<Itemline, Integer>{
	List<Itemline> findByOrder(Orders order);
	Itemline findBySeq(Integer seq);
//	參數命名要與SQL內定義的變數名稱相同
	@Procedure("itemQty")
	void updateItemQty(Integer input_seq, Integer input_qty);
}
