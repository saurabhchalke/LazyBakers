package lazybakers.model.dto;

import java.util.List;

public class BillDTO {
	
	float tax;
	float totalCost;
	List<PizzaOrderDTO> items;
	
	public float getTax() {
		return tax;
	}
	public void setTax(float tax) {
		this.tax = tax;
	}
	public float getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(float totalCost) {
		this.totalCost = totalCost;
	}
	public List<PizzaOrderDTO> getItems() {
		return items;
	}
	public void setItems(List<PizzaOrderDTO> items) {
		this.items = items;
	}
	
}
