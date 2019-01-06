package com.moshe.final_project2.entity;


import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



public class Income {

	private long id;
	private String name;
	private LocalDateTime date;
	private IncomeType description;
	private double amount;

	
	public Income() {
		super();
	}


	public Income(String name, LocalDateTime date, double amount,IncomeType description) {
		super();
//		this.id = id;
		this.name = name;
		this.date = date;
		this.description = description;
		this.amount = amount;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public LocalDateTime getDate() {
		return date;
	}


	public void setDate(LocalDateTime date) {
		this.date = date;
	}


	public IncomeType getDescription() {
		return description;
	}


	public void setDescription(IncomeType description) {
		this.description = description;
	}


	public double getAmount() {
		return amount;
	}


	public void setAmount(double amount) {
		this.amount = amount;
	}


	@Override
	public String toString() {
		return "Income id:" + id + ", name:" + name + ", date:" + date + ", description:" + description + ", amount:"
				+ amount;
	}
	
	
	
	
}
