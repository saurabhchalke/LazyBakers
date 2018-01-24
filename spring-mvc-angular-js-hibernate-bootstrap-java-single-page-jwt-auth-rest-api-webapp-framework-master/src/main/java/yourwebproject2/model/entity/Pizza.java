package yourwebproject2.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Pizza {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int pizzaId;
	private String pizzaName;
	private String pizzaDesc;
	private float price;
	private int size;
	private boolean customized;
	@OneToOne
	private Base base;
	
	public Pizza(String pizzaName, String pizzaDesc, float price, int size, boolean customized, Base base) {
		super();
		this.pizzaName = pizzaName;
		this.pizzaDesc = pizzaDesc;
		this.price = price;
		this.size = size;
		this.customized = customized;
		this.base = base;
	}

	public String getPizzaName() {
		return pizzaName;
	}

	public void setPizzaName(String pizzaName) {
		this.pizzaName = pizzaName;
	}

	public String getPizzaDesc() {
		return pizzaDesc;
	}

	public void setPizzaDesc(String pizzaDesc) {
		this.pizzaDesc = pizzaDesc;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public boolean isCustomized() {
		return customized;
	}

	public void setCustomized(boolean customized) {
		this.customized = customized;
	}

	public Base getBase() {
		return base;
	}

	public void setBase(Base base) {
		this.base = base;
	}
	
	
}
