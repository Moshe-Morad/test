package com.moshe.final_project2.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table
public class Company {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long companyId;
	private String compName;
	private String password;
	private String email;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = " COMPANY_COUPON",
	joinColumns = @JoinColumn(name = "COUPON_ID"),
	inverseJoinColumns = @JoinColumn(name = "COMPANY_ID"))
	private Set<Coupon> coupons;
	
	public Company() {
		
	}

	public Company(long id, String compName, String password, String email, Set<Coupon> coupons) {
		super();
		this.companyId = id;
		this.compName = compName;
		this.password = password;
		this.email = email;
		this.coupons = coupons;
	}

	public Long getId() {
		return companyId;
	}

	public void setId(Long id) {
		this.companyId = id;
	}

	public String getCompName() {
		return compName;
	}

	public void setCompName(String compName) {
		this.compName = compName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<Coupon> getCoupons() {
		return coupons;
	}

	public void setCoupons(Set<Coupon> coupons) {
		this.coupons = coupons;
	}

	@Override
	public String toString() {
		return "Company id :" + companyId + ", compName:" + compName + 
				", password:" + password + ", email:" + email ;
	}


	
	
	
	
	
	
	
}
