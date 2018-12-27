package com.moshe.final_project2.service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.hibernate.type.SetType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moshe.final_project2.entity.Company;
import com.moshe.final_project2.entity.Coupon;
import com.moshe.final_project2.entity.CouponType;
import com.moshe.final_project2.repository.CompanyRepository;
import com.moshe.final_project2.repository.CouponRepository;
import com.moshe.final_project2.repository.CustomerRepository;

import ExceptionController.CustomException;

@Service
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	CouponRepository couponReposiroty;
	@Autowired
	CustomerRepository customerRepository;
	@Autowired
	CompanyRepository companyRepository;

	@Override
	public Coupon addNewCoupon(Coupon newCouponAdded, long companyId) throws CustomException {
		Company compId = companyRepository.findById(companyId);
		Optional<Coupon> newCoupon = couponReposiroty.findByTitle(newCouponAdded.getTitle());
		if (newCoupon.isPresent()) {
			throw new CustomException("this Coupon is allradey exsisting");
		}
		Set<Coupon> companyCoupon = compId.getCoupons();
		companyCoupon.add(newCouponAdded);
		return couponReposiroty.save(newCouponAdded);

	}

	@Override
	public Coupon deleteCoupon(Coupon deleteCoupon) throws CustomException {
		Coupon removeCoupon = findCouponById(deleteCoupon.getId());
		if (removeCoupon == null) {
			throw new CustomException();
		}
		couponReposiroty.delete(deleteCoupon);
		return removeCoupon;
	}

	@Override
	public Coupon upDateCoupon(Coupon couponToUpDate) {
		Coupon couponFromDb = couponReposiroty.findById(couponToUpDate.getId()).get();
		couponFromDb.setEndDate(couponToUpDate.getEndDate());
		couponFromDb.setPrice(couponToUpDate.getPrice());
		couponToUpDate = couponReposiroty.save(couponFromDb);
		return couponToUpDate;
	}

	@Override
	public Coupon findCouponById(long findCouponById) {
		Coupon couponId = couponReposiroty.findById(findCouponById);
		return couponId;
	}

	@Override
	public Collection<Coupon> findAllCoupons() {
		List<Coupon> coupons = couponReposiroty.findAll();
		return coupons;
	}

	@Override
	public Collection<Coupon> findCouponByType(CouponType findCouponByType) {
		Collection<Coupon> couponType = couponReposiroty.findByType(findCouponByType);
		return couponType;
	}

}
