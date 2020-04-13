package com.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;

@Entity
@Table(name = "item")
@NamedStoredProcedureQuery(name = "priceRange", 
procedureName = "priceRange", parameters = {
  @StoredProcedureParameter(mode = ParameterMode.IN, name = "price_in", type = Integer.class),
  @StoredProcedureParameter(mode = ParameterMode.INOUT, name = "countRow_out", type = Integer.class)})
public class Item {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int iid;
	private String iname;
	private String type;
	private Integer price;
	private Date lifedate;
	

	public Item(int iid, String iname, String type, int price) {
		this.iid = iid;
		this.iname = iname;
		this.type = type;
		this.price = price;
	}
	public Item() {}
	public int getIid() {
		return iid;
	}
	public void setIid(int iid) {
		this.iid = iid;
	}
	public String getIname() {
		return iname;
	}
	public void setIname(String iname) {
		this.iname = iname;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public Date getLifedate() {
		return lifedate;
	}
	public void setLifedate(Date lifedate) {
		this.lifedate = lifedate;
	}
	
	

}