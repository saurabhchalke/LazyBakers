package lazybakers.model.entity;

import java.util.Date;

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
	@Column(name = "bill_id")
	private int billId;
	@Column(name = "bill_date")
	private Date billDate;
	@Column(name = "total_items")
	private int totalItems;
	@Column(name = "bill_amount")
	private float billAmount;
	private float tax;
	private String stage;
	private int feedback;
	@Column(nullable=true)
	private String comments;
	@Column(name = "offer_type")
	private String offerType;
	@OneToOne
	private Coupon coupon;
	@OneToOne
	private Address address;
	@OneToOne
	private User user;
	
	public Bill(Date billDate, int totalItems, float billAmount, float tax, String stage, int feedback,
			String comments, String offerType, Coupon coupon, Address address, User user) {
		super();
		this.billDate = billDate;
		this.totalItems = totalItems;
		this.billAmount = billAmount;
		this.tax = tax;
		this.stage = stage;
		this.feedback = feedback;
		this.comments = comments;
		this.offerType = offerType;
		this.coupon = coupon;
		this.address = address;
		this.user = user;
	}
	
	public Bill() {
		
	}

	public Date getBillDate() {
		return billDate;
	}

	public void setBillDate(Date billDate) {
		this.billDate = billDate;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
