package lazybakers.model.repository;

import lazybakers.model.entity.Coupon;

public interface CouponRepository {
	
	public void createCoupon(String couponCode, float offerPerc, float maxAmount);
	
	public Coupon getCouponByCode(String couponCode);

}
