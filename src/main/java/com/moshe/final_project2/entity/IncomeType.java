package com.moshe.final_project2.entity;

public enum IncomeType {
	CUSTOMER_PURCHASE("desc..."),
	COMPANY_NEW_COUPON("desc..."),
	COMPANY_UPDATE_COUPON("desc...");
	
	private String description;
	
	private IncomeType(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return this.description;
	}

	
	
	
	
}
