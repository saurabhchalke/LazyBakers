package lazybakers.model.dto;

public class PizzaOrderDTO {
	
	String id;
	String name;
	float price;
	int quantity;
	float total;
	PizzaDTO data;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public PizzaDTO getData() {
		return data;
	}
	public void setData(PizzaDTO data) {
		this.data = data;
	}
	
	
	
}
