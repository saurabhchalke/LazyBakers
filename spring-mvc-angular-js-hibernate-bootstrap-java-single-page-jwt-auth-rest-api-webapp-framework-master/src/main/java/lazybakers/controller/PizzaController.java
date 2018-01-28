package lazybakers.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

@Controller
public class PizzaController {
	
	private static Logger LOG = LoggerFactory.getLogger(PizzaController.class);
	@Autowired
	PizzaService pizzaService;
	@Autowired
	BaseService baseService;
	
	@RequestMapping(value = "/pizza", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<Pizza> getallpizza() {
		return pizzaService.getAllPizza();
	}
	
	/*@RequestMapping(value = "/pizza", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public Integer addpizza(@RequestBody PizzaDTO pizzaDTO) {
		Base base = baseService.getBaseById(pizzaDTO.getBaseId());
		return pizzaService.createPizza(pizzaDTO.getPizzaName(), pizzaDTO.getPizzaDesc(), pizzaDTO.getPrice(), pizzaDTO.getSize(), pizzaDTO.isCustomized(), base);
	}*/
	
	@RequestMapping(value = "/pizza/{pId}", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public Pizza getpizzabyid(@PathVariable int pId) {
		return pizzaService.getPizzaById(pId);
	}

}
