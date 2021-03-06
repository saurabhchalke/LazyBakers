package lazybakers.model.repository;

import java.util.List;

import lazybakers.model.entity.Topping;

public interface ToppingRepository {
	
	public Integer createTopping(String toppingname, float price, int stock, boolean vegetarian);
	
	public void updateTopping(int toppindId);
	
	public void deleteTopping(int toppingId);
	
	public Topping getToppingById(int toppingId);
	
	public List<Topping> getAllTopping();
	
}
