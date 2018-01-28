package lazybakers.model.dto;

public class ToppingDTO {
	String toppingname;
	float price;
	int stock;
	boolean vegetarian;
	
	public String gettoppingname() {
		return toppingname;
	}
	public void settoppingname(String toppingname) {
		this.toppingname = toppingname;
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
