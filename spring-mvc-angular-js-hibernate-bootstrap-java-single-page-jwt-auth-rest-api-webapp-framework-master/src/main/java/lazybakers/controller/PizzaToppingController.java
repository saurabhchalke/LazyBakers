package lazybakers.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import lazybakers.model.entity.PizzaTopping;
import lazybakers.service.PizzaToppingService;

@Controller
public class PizzaToppingController {
	
	private static Logger LOG = LoggerFactory.getLogger(ToppingController.class);
	@Autowired
	PizzaToppingService pizzaToppingService;
	
	@RequestMapping(value = "/pizzatopping/pizza/{pId}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<PizzaTopping> getPizzaToppingByPizza(@PathVariable int pId) {
        return pizzaToppingService.getPizzaToppingByPizzaId(pId);
    }

}
