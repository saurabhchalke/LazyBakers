package lazybakers.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Customer {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int customerId;
	private String customerFirstName;
	private String customerLastName;
	private String password;
	@Column(unique=true)
	private String mobilenumber;
	private int points;
	@Column(unique=true)
	private String customerEmail;
	
	public Customer() {
		
	}

	public Customer(String customerFirstName, String customerLastName, String password, String mobilenumber, int points,
			String customerEmail) {
		super();
		this.customerFirstName = customerFirstName;
		this.customerLastName = customerLastName;
		this.password = password;
		this.mobilenumber = mobilenumber;
		this.points = points;
		this.customerEmail = customerEmail;
	}

	public String getCustomerFirstName() {
		return customerFirstName;
	}

	public void setCustomerFirstName(String customerFirstName) {
		this.customerFirstName = customerFirstName;
	}

	public String getCustomerLastName() {
		return customerLastName;
	}

	public void setCustomerLastName(String customerLastName) {
		this.customerLastName = customerLastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getmobilenumber() {
		return mobilenumber;
	}

	public void setmobilenumber(String mobilenumber) {
		this.mobilenumber = mobilenumber;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}
	
}
