package lazybakers.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "pizza_order")
public class PizzaOrder {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "order_id")
	private int orderId;
	private float price;
	private int quantity;
	private float total;
	@ManyToOne
	private Bill bill;
	@OneToOne
	private Pizza pizza;
	
	public PizzaOrder(float price, int quantity, float total, Bill bill, Pizza pizza) {
		super();
		this.price = price;
		this.quantity = quantity;
		this.total = total;
		this.bill = bill;
		this.pizza = pizza;
	}
	
	public PizzaOrder() {
		
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	public Bill getBill() {
		return bill;
	}

	public void setBill(Bill bill) {
		this.bill = bill;
	}

	public Pizza getPizza() {
		return pizza;
	}

	public void setPizza(Pizza pizza) {
		this.pizza = pizza;
	}

	
}
