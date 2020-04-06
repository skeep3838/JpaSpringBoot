package com.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "itemline")
public class Itemline {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer seq;
	private Integer qty;
	@ManyToOne
	@JoinColumn(name = "iid", nullable = false)
	private Item item;

	@ManyToOne(targetEntity = Orders.class, fetch = FetchType.LAZY)
//	@JoinColumn(name="oid", nullable=false)
	private Orders order;

	public Itemline(Integer seq, Item item, Integer qty, Orders order) {
		super();
		this.seq = seq;
		this.item = item;
		this.qty = qty;
		this.order = order;
	}

	public Itemline() {
	}

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
		return order;
	}

	public void setOrders(Orders orders) {
		this.order = orders;
	}

}