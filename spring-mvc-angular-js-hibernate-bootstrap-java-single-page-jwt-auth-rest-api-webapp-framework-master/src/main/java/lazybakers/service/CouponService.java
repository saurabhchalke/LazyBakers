package lazybakers.service;

import lazybakers.model.entity.Coupon;

public interface CouponService {
	
	public void createCoupon(String couponCode, float offerPerc, float maxAmount);
	
	public Coupon getCouponByCode(String couponCode);
	
}
