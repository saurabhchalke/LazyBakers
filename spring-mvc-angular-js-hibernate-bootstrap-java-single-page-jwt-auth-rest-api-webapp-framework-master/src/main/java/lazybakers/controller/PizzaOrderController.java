package lazybakers.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import lazybakers.model.entity.PizzaOrder;
import lazybakers.service.BillService;
import lazybakers.service.PizzaOrderService;
import lazybakers.service.PizzaService;

@Controller
public class PizzaOrderController {
	
	@Autowired
	PizzaOrderService pizzaOrderService;
	
	@Autowired
	BillService billService;
	
	@Autowired
	PizzaService pizzaService;
	
	@RequestMapping(value = "/pizzaorder/order/{oId}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<PizzaOrder> gettopping(@PathVariable int oId){
        return pizzaOrderService.getPizzaOrderByBillId(oId);
    }

}
