package yourwebproject2.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Bill {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int billId;
	private long timestampMillis;
	private int totalItems;
	private float billAmount;
	private float tax;
	private String stage;
	private int feedback;
	@Column(nullable=true)
	private String comments;
	private String offerType;
	private String mobileNumber;
	@OneToOne
	private Coupon coupon;
	@OneToOne
	private Address address;
	@OneToOne
	private Customer customer;
	
	public Bill(long timestampMillis, int totalItems, float billAmount, float tax, String stage, int feedback,
			String comments, String offerType, String mobileNumber, Coupon coupon, Address address, Customer customer) {
		super();
		this.timestampMillis = timestampMillis;
		this.totalItems = totalItems;
		this.billAmount = billAmount;
		this.tax = tax;
		this.stage = stage;
		this.feedback = feedback;
		this.comments = comments;
		this.offerType = offerType;
		this.mobileNumber = mobileNumber;
		this.coupon = coupon;
		this.address = address;
		this.customer = customer;
	}

	public long getTimestampMillis() {
		return timestampMillis;
	}

	public void setTimestampMillis(long timestampMillis) {
		this.timestampMillis = timestampMillis;
	}

	public int getTotalItems() {
		return totalItems;
	}

	public void setTotalItems(int totalItems) {
		this.totalItems = totalItems;
	}

	public float getBillAmount() {
		return billAmount;
	}

	public void setBillAmount(float billAmount) {
		this.billAmount = billAmount;
	}

	public float getTax() {
		return tax;
	}

	public void setTax(float tax) {
		this.tax = tax;
	}

	public String getStage() {
		return stage;
	}

	public void setStage(String stage) {
		this.stage = stage;
	}

	public int getFeedback() {
		return feedback;
	}

	public void setFeedback(int feedback) {
		this.feedback = feedback;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getOfferType() {
		return offerType;
	}

	public void setOfferType(String offerType) {
		this.offerType = offerType;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public Coupon getCoupon() {
		return coupon;
	}

	public void setCoupon(Coupon coupon) {
		this.coupon = coupon;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	
}
