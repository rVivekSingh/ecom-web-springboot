package com.example.ecomweb.models;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.sun.istack.NotNull;

@Entity
public class Orders {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotNull
	private Long itemId;

	@NotNull
	private Integer customerId;

	@NotNull
	private Double price;

	private LocalDateTime orderDate;

	private LocalDateTime deliveryDate;

	private String courierPartner;
	
	private Integer numOfItems;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public LocalDateTime getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDateTime orderDate) {
		this.orderDate = orderDate;
	}

	public LocalDateTime getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(LocalDateTime deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String getCourierPartner() {
		return courierPartner;
	}

	public void setCourierPartner(String courierPartner) {
		this.courierPartner = courierPartner;
	}

	public Integer getNumOfItems() {
		return numOfItems;
	}

	public void setNumOfItems(Integer numOfItems) {
		this.numOfItems = numOfItems;
	}

	@Override
	public String toString() {
		return "Orders [id=" + id + ", itemId=" + itemId + ", customerId=" + customerId + ", price=" + price
				+ ", orderDate=" + orderDate + ", deliveryDate=" + deliveryDate + ", courierPartner=" + courierPartner
				+ ", numOfItems=" + numOfItems + "]";
	}

}
