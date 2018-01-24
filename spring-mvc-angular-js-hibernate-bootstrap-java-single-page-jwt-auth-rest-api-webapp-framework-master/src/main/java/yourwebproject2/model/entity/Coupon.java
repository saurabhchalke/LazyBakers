package yourwebproject2.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Coupon {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int couponId;
	private float offerPerc;
	private float maxAmount;
	
	public Coupon(float offerPerc, float maxAmount) {
		super();
		this.offerPerc = offerPerc;
		this.maxAmount = maxAmount;
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
