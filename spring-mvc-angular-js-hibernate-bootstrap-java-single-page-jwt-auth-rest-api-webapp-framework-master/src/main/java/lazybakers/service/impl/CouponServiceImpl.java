package lazybakers.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lazybakers.model.entity.Coupon;
import lazybakers.model.repository.CouponRepository;
import lazybakers.service.CouponService;

@Service("couponService")
@Transactional
public class CouponServiceImpl implements CouponService {
	
	@Autowired
	CouponRepository couponRepository;

	@Override
	public void createCoupon(String couponCode, float offerPerc, float maxAmount) {
		couponRepository.createCoupon(couponCode, offerPerc, maxAmount);
	}

	@Override
	public Coupon getCouponByCode(String couponCode) {
		return couponRepository.getCouponByCode(couponCode);
	}
	
	

}
