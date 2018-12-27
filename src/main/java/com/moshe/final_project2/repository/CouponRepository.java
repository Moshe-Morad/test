package com.moshe.final_project2.repository;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moshe.final_project2.entity.Company;
import com.moshe.final_project2.entity.Coupon;
import com.moshe.final_project2.entity.CouponType;

public interface CouponRepository extends JpaRepository<Coupon, Long> {

	Optional<Coupon> findByTitle(String title);
	Coupon findById(long couponId);
	Collection<Coupon> findByType(CouponType type);
//	Optional<Coupon> addCoupon(Coupon purchasedCoupon);


}
