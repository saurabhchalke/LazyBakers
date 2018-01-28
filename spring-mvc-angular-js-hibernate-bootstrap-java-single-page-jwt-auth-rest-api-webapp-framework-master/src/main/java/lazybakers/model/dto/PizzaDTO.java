package lazybakers.model.dto;

import java.util.List;

public class PizzaDTO {
	
	String pizzaName;
	String pizzaDesc;
	boolean customized;
	float price;
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
	public boolean isCustomized() {
		return customized;
	}
	public void setCustomized(boolean customized) {
		this.customized = customized;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	List<Integer> toppings;
	int baseId;
	String size;
	
	public List<Integer> getToppings() {
		return toppings;
	}
	public void setToppings(List<Integer> toppings) {
		this.toppings = toppings;
	}
	public int getBaseId() {
		return baseId;
	}
	public void setBaseId(int baseId) {
		this.baseId = baseId;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	

}
