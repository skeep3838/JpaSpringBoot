package com.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

@Entity
@Table(name = "customer")
//@NamedQuery(query = "Select c from Customer c where c.cname = :name", name = "find customer by name")
public class Customer {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int cid;
	private String cname;
	private String password;

	//將資料以typeID欄位 ASC方式排列後再寫進Set內
	@OneToMany(mappedBy="customer", orphanRemoval = false, fetch =FetchType.EAGER)
	@OrderBy("oid")
	Set<Orders> orderList;

	public Customer(int cid, String cname, String password, Set<Orders> orderList) {
		super();
		this.cid = cid;
		this.cname = cname;
		this.password = password;
		this.orderList = orderList;
	}
	
	public Customer(int cid, String cname, String password) {
		super();
		this.cid = cid;
		this.cname = cname;
		this.password = password;
	}

	public Customer() {
		super();
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@SuppressWarnings("rawtypes")
	public Set getOrderList() {
		return orderList;
	}
	
	
}