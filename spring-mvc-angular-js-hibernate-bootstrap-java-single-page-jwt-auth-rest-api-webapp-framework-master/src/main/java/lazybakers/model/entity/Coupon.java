package lazybakers.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Coupon {
	
	@Id
	@Column(name = "coupon_code")
	private String couponCode;
	@Column(name = "offer_perc")
	private float offerPerc;
	@Column(name = "max_amount")
	private float maxAmount;
	
	
	
	public Coupon() {

	}

	public Coupon(String couponCode, float offerPerc, float maxAmount) {
		super();
		this.couponCode = couponCode;
		this.offerPerc = offerPerc;
		this.maxAmount = maxAmount;
	}

	public String getCouponCode() {
		return couponCode;
	}

	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}

	public float getOfferPerc() {
		return offerPerc;
	}

	public void setOfferPerc(float offerPerc) {
		this.offerPerc = offerPerc;
	}

	public float getMaxAmount() {
		return maxAmount;
	}

	public void setMaxAmount(float maxAmount) {
		this.maxAmount = maxAmount;
	}
	
	
}
