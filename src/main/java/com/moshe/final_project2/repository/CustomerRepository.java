package com.moshe.final_project2.repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.moshe.final_project2.entity.Company;
import com.moshe.final_project2.entity.Coupon;
import com.moshe.final_project2.entity.CouponType;
import com.moshe.final_project2.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

	Customer findByCustomerName(String customerName);
	Customer findById(long customerId);
	boolean existsByCustomerName(String customerName);
	List<Customer> findAll();
	
	@Query("SELECT coupon FROM Customer customer JOIN customer.coupons AS coupon WHERE customer.id=:id AND coupon.type=:couponType")
	Collection<Coupon> findAllPurchasedCouponsByType(@Param("id") long custId, @Param("couponType") CouponType type);
	
	@Query("SELECT coup FROM Customer cust JOIN cust.coupons AS coup WHERE cust.id=:id AND coup.price>=:minPrice AND coup.price<=:maxPrice")
	Collection<Coupon> findAllPurchasedCouponsByPrice(@Param("id") long custId,  @Param("maxPrice") double maximumPrice);

	
	

	
	

}
