package com.example.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
@Entity
@Table(name="CGI_OrderItem")
public class OrderItem {
	@Id
	@Column(name="Order_Item_Code")
	   
	private int orderItemCode;
	private int qty;
	private int amount;
	@OneToOne(cascade=CascadeType.ALL)
	 @JoinColumn(name="ItemCode")
	private Item item;
	
	public int getOrderItemCode() {
		return orderItemCode;
	}
	public void setOrderItemCode(int orderItemCode) {
		this.orderItemCode = orderItemCode;
	}
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	
	
}
