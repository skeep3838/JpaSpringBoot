package com.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.model.Itemline;


public interface ItemlineDao extends JpaRepository<Itemline, Integer>{
//	List<Itemline> findItemlineByOid(Integer oid);
}
