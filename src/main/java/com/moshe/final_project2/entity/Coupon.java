package com.moshe.final_project2.entity;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
@Table
public class Coupon {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long couponId;
	private String title;
	@Column(name = "start_date")
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate startDate;
	@Column(name = "end_date")
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate endDate;
	private int amount;
	private CouponType type;
	private String message;
	private double price;
	private String image;
	
	
	public Coupon() {
		
	}

	public Coupon(Long id, String title, LocalDate startDate, LocalDate endDate, int amount, CouponType type,
			String message, double price, String image) {
		super();
		this.couponId = id;
		this.title = title;
		this.startDate = startDate;
		this.endDate = endDate;
		this.amount = amount;
		this.type = type;
		this.message = message;
		this.price = price;
		this.image = image;
	}

	public Long getId() {
		return couponId;
	}

	public void setId(Long id) {
		this.couponId = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public CouponType getType() {
		return type;
	}

	public void setType(CouponType type) {
		this.type = type;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "Coupon id:" + couponId + ", title:" + title 
							+ ", startDate:" + startDate 
							+ ", endDate:" + endDate
							+ ", amount:" + amount 
							+ ", type:" + type 
							+ ", message:" + message 
							+ ", price:" + price 
							+ ", image:"+ image;
	}
	
	
}
