package lazybakers.controller;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import lazybakers.model.dto.PizzaDTO;
import lazybakers.model.entity.Pizza;
import lazybakers.model.entity.Base;
import lazybakers.service.BaseService;
import lazybakers.service.PizzaService;
import lazybakers.service.PizzaToppingService;
import lazybakers.service.ToppingService;

@Controller
public class PizzaController {
	
	@Autowired
	PizzaService pizzaService;
	@Autowired
	BaseService baseService;
	@Autowired
	PizzaToppingService pizzaToppingService;
	@Autowired
	ToppingService toppingService;
	
	@RequestMapping(value = "/pizza", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<Pizza> getallpizza() {
		return pizzaService.getAllPizza();
	}
	
	@RequestMapping(value = "/pizza", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public Integer addpizza(@RequestBody PizzaDTO pizzaDTO) {
		Base base = baseService.getBaseById(pizzaDTO.getBaseId());
		int pizzaId = pizzaService.createPizza(pizzaDTO.getPizzaName(), pizzaDTO.getPizzaDesc(), pizzaDTO.getPrice(), pizzaDTO.getSize(), pizzaDTO.isCustomized(), base);
		Iterator<Integer> it = pizzaDTO.getToppings().iterator();
		while(it.hasNext()) {
			pizzaToppingService.createPizzaTopping(pizzaService.getPizzaById(pizzaId), toppingService.getToppingById(it.next()));
		}
		return pizzaId;
	}
	
	@RequestMapping(value = "/pizza/{pId}", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public Pizza getpizzabyid(@PathVariable int pId) {
		return pizzaService.getPizzaById(pId);
	}

}
