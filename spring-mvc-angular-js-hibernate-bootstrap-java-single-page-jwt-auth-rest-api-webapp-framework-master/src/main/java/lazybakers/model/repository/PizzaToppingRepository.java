package lazybakers.model.repository;

import java.util.List;

import lazybakers.model.entity.Pizza;
import lazybakers.model.entity.PizzaTopping;
import lazybakers.model.entity.Topping;

public interface PizzaToppingRepository {
	
	public Integer createPizzaTopping(Pizza pizza, Topping topping);
	
	public List<PizzaTopping> getPizzaToppingByPizzaId(int pizzaId);

}
