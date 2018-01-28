package lazybakers.model.dto;

import java.util.List;

public class PizzaDTO {
	
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
