package yourwebproject2.model.entity;

import javax.persistence.Column;
//import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
//import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;

@Entity
public class Address {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int addressId;
	private int houseNo;
	private String street;
	private String city;
	@Column(nullable=true)
	private String state;
	private String pincode;
	//@JoinColumn()
	//@Column(name="customerId")
	@ManyToOne
	private Customer customer;
	
	public Address(/*int addressId,*/ int houseNo, String street, String city, String state, String pincode,
			Customer customer) {
		super();
		//this.addressId = addressId;
		this.houseNo = houseNo;
		this.street = street;
		this.city = city;
		this.state = state;
		this.pincode = pincode;
		this.customer = customer;
	}

	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}

	public int getHouseNo() {
		return houseNo;
	}

	public void setHouseNo(int houseNo) {
		this.houseNo = houseNo;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}
