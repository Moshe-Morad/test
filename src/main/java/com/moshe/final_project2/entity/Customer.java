package com.moshe.final_project2.entity;

import java.util.Collection;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long customerId;
	private String customerName;
	private String customerPassword;
	private String customerEmail;
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name = "CUSTOMER_COUPON",
	joinColumns = @JoinColumn(name = "CUSTOMER_ID"),
	inverseJoinColumns = @JoinColumn(name = "COUPON_ID"))
	private Set<Coupon> coupons;
	
	public Customer() {
		
	}

	public Customer(long id, String customerName, String customerPassword, String customerEmail, Set<Coupon> coupons) {
		super();
		this.customerId = id;
		this.customerName = customerName;
		this.customerPassword = customerPassword;
		this.customerEmail = customerEmail;
		this.coupons = coupons;
	}

	public long getId() {
		return customerId;
	}

	public void setId(long id) {
		this.customerId = id;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerPassword() {
		return customerPassword;
	}

	public void setCustomerPassword(String customerPassword) {
		this.customerPassword = customerPassword;
	}
	
	public String getCustomerEmail() {
		return customerEmail;
	}
	
	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public Set<Coupon> getCoupons() {
		return coupons;
	}

	public void setCoupons(Set<Coupon> coupons) {
		this.coupons = coupons;
	}

	@Override
	public String toString() {
		return "Customer id:" + customerId 
				+ ", customerName:" + customerName 
				+ ", customerPassword:" + customerPassword
				+ ", Customer Email: " + customerEmail
				+ ", coupons:" + coupons;
	}
	
	
}
