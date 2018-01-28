package lazybakers.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lazybakers.model.entity.Base;
import lazybakers.model.entity.Pizza;
import lazybakers.model.repository.PizzaRepository;
import lazybakers.service.PizzaService;

@Service("pizzaService")
@Transactional
public class PizzaServiceImpl implements PizzaService {
	
	@Autowired
	PizzaRepository pizzaRepository;

	@Override
	public Integer createPizza(String pizzaName, String pizzaDesc, float price, String size, boolean customized,
			Base base) {
		return pizzaRepository.createPizza(pizzaName, pizzaDesc, price, size, customized, base);
	}

	@Override
	public List<Pizza> getAllPizza() {
		return pizzaRepository.getAllPizza();
	}

	@Override
	public Pizza getPizzaById(int pizzaId) {
		return pizzaRepository.getPizzaById(pizzaId);
	}

}
