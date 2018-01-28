package lazybakers.model.dto;

public class CouponDTO {
	
	String couponCode;
	float offerPerc;
	float maxAmount;
	
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
