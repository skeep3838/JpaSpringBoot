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
@NamedStoredProcedureQuery(name = "price_Range_Item", 
procedureName = "price_Range_Item", resultClasses = Item.class,
parameters = @StoredProcedureParameter(mode = ParameterMode.IN, name = "price_in", type = Integer.class))
public class Item {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer iid;
	private String iname;
	private String type;
	private Integer price;
	private Date lifedate;
	

	public Item(Integer iid, String iname, String type, Integer price) {
		this.iid = iid;
		this.iname = iname;
		this.type = type;
		this.price = price;
	}
	
	public Item(Integer iid, String iname, Date lifedate, Integer price, String type) {
		this.iid = iid;
		this.iname = iname;
		this.type = type;
		this.price = price;
		this.lifedate = lifedate;
	}
	public Item() {}
	public Integer getIid() {
		return iid;
	}
	public void setIid(Integer iid) {
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
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public Date getLifedate() {
		return lifedate;
	}
	public void setLifedate(Date lifedate) {
		this.lifedate = lifedate;
	}
	
	

}