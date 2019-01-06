package com.moshe.final_project2.repository;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.moshe.final_project2.entity.Company;
import com.moshe.final_project2.entity.Coupon;
import com.moshe.final_project2.entity.CouponType;

public interface CouponRepository extends JpaRepository<Coupon, Long> {

	Coupon findByTitle(String title);
	Coupon findById(long couponId);
	Collection<Coupon> findByType(CouponType type);
	Collection<Coupon> findByendDate(LocalDate endDate);
	boolean existsByTitle(String title);
	
	@Query("SELECT endDate FROM Coupon")
	Coupon deleteCouponsThatExpired();
	
	


}
