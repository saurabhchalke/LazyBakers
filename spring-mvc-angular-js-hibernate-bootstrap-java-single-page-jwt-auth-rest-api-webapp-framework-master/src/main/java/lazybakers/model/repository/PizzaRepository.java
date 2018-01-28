package lazybakers.model.repository;

import java.util.List;

import lazybakers.model.entity.Base;
import lazybakers.model.entity.Pizza;

public interface PizzaRepository {
	
	public Integer createPizza(String pizzaName, String pizzaDesc, float price, String size, boolean customized, Base base);
	
	public List<Pizza> getAllPizza();
	
	public Pizza getPizzaById(int pizzaId);

}
