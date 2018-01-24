package yourwebproject2.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Topping {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int toppingId;
	private String toppingName;
	private float price;
	private int stock;
	private boolean vegetarian;
	
	public Topping(int toppingId, String toppingName, float price, int stock, boolean vegetarian) {
		super();
		this.toppingId = toppingId;
		this.toppingName = toppingName;
		this.price = price;
		this.stock = stock;
		this.vegetarian = vegetarian;
	}

	public int getToppingId() {
		return toppingId;
	}

	public void setToppingId(int toppingId) {
		this.toppingId = toppingId;
	}

	public String getToppingName() {
		return toppingName;
	}

	public void setToppingName(String toppingName) {
		this.toppingName = toppingName;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public boolean isVegetarian() {
		return vegetarian;
	}

	public void setVegetarian(boolean vegetarian) {
		this.vegetarian = vegetarian;
	}

}
