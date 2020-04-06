package com.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

@Entity
@Table(name = "orders")
public class Orders {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer oid;
	private Date createdate;
	private Integer shipment;
	@ManyToOne
	@JoinColumn(name = "cid", nullable = false)
	private Customer customer;

//	fetch = FetchType.EAGER 加入這個會導致撈出的Bean式舊的資料
//	mappedBy="orderMap",  => 在Itemline 找到對應的 private Orders orderMap
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name="order_oid")
	@OrderBy("seq")
	private List<Itemline> orderDetail = new ArrayList<Itemline>();

	public Orders(Date createdate, Integer shipment, Customer customer) {
		this.createdate = createdate;
		this.shipment = shipment;
		this.customer = customer;
	}

	public Orders(Date createdate, Integer shipment, Customer customer, List<Itemline> orderDetail) {
		this.createdate = createdate;
		this.shipment = shipment;
		this.customer = customer;
		this.orderDetail = orderDetail;
	}

	public Orders() {
	}

	public Integer getOid() {
		return oid;
	}

	public void setOid(Integer oid) {
		this.oid = oid;
	}

	public Date getCreateDate() {
		return createdate;
	}

	public void setCreateDate(Date createdate) {
		this.createdate = createdate;
	}

	public int getShipment() {
		return shipment;
	}

	public void setShipment(int shipment) {
		this.shipment = shipment;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setOrderDetail(List<Itemline> orderDetail) {
		this.orderDetail = orderDetail;
	}

	public List<Itemline> getOrderDetail() {
		return orderDetail;
	}

}
