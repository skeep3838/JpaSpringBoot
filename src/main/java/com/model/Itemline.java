package com.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;


@Entity
@Table(name = "itemline")
@PrimaryKeyJoinColumn(referencedColumnName = "oid")
public class Itemline {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer seq;
	private Integer qty;
	@ManyToOne
	@JoinColumn(name="iid", nullable=false)
	private Item item;
	
//	拿掉@JoinColumn(name="oid", nullable=false) 一樣會發生oid無法同時更新的情形
	@ManyToOne   
	@JoinColumn(name="oid", nullable=false)
	private Orders orderMap;
	
//	錯誤訊息
//	@OneToOne or @ManyToOne on com.model.Itemline.oid references an unknown entity
//	@ManyToOne
//	@JoinColumn(name="oid", nullable=false)
//	private Integer oid;
	
	public Itemline(Integer seq, Item item, Integer qty, Orders orderMap) {
		super();
		this.seq = seq;
		this.item = item;
		this.qty = qty;
		this.orderMap = orderMap;
	}
	
	public Itemline() {}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public Integer getQty() {
		return qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Orders getOrders() {
		return orderMap;
	}

	public void setOrders(Orders orders) {
		this.orderMap = orders;
	}
	
	

	
	
}