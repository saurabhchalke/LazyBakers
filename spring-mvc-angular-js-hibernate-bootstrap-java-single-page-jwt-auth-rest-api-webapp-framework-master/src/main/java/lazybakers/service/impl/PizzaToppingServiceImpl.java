package lazybakers.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lazybakers.model.entity.Pizza;
import lazybakers.model.entity.PizzaTopping;
import lazybakers.model.entity.Topping;
import lazybakers.model.repository.PizzaToppingRepository;
import lazybakers.service.PizzaToppingService;

@Service("pizzaToppingService")
@Transactional
public class PizzaToppingServiceImpl implements PizzaToppingService {
	
	@Autowired
	PizzaToppingRepository pizzaToppingRepository;

	@Override
	public Integer createPizzaTopping(Pizza pizza, Topping topping) {
		return pizzaToppingRepository.createPizzaTopping(pizza, topping);
	}

	@Override
	public List<PizzaTopping> getPizzaToppingByPizzaId(int pizzaId) {
		return pizzaToppingRepository.getPizzaToppingByPizzaId(pizzaId);
	}

}
