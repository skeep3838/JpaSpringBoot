package com.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;


@Entity
@Table(name = "orders")
@Inheritance(strategy = InheritanceType.JOINED)
//@NamedQuery(query="SELECT MAX(oid) FROM Orders", name="Orders.newOrderId")
public class Orders {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer oid;
	private Date createdate;
	private Integer shipment;
	@ManyToOne
	@JoinColumn(name="cid", nullable=false)
	private Customer customer;
	
//	fetch = FetchType.EAGER 加入這個會導致撈出的Bean式舊的資料
//	mappedBy="orderMap",  => 在Itemline 找到對應的 private Orders orderMap
	@OneToMany(mappedBy="orderMap", cascade=CascadeType.ALL)
	@OrderBy("seq")
	List<Itemline> orders;
	
	public Orders(Integer oid, Date createdate, Integer shipment, Customer customer) {
		this.oid = oid;
		this.createdate = createdate;
		this.shipment = shipment;
		this.customer = customer;
	}
	
	public Orders(Integer oid, Date createdate, Integer shipment, Customer customer, List<Itemline> orderDetail) {
		this.oid = oid;
		this.createdate = createdate;
		this.shipment = shipment;
		this.customer = customer;
		this.orders = orderDetail;
	}
	
	public Orders() {}

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
		this.orders = orderDetail;
	}
	public List<Itemline> getOrderDetail() {
		return orders;
	}

	
}
