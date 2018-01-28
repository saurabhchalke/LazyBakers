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

import lazybakers.model.dto.PizzaOrderDTO;
import lazybakers.model.dto.ToppingDTO;
import lazybakers.model.entity.Bill;
import lazybakers.model.entity.Pizza;
import lazybakers.model.entity.PizzaOrder;
import lazybakers.model.entity.Topping;
import lazybakers.service.BillService;
import lazybakers.service.PizzaOrderService;
import lazybakers.service.PizzaService;
import lazybakers.service.ToppingService;

@Controller
public class PizzaOrderController {
	
	private static Logger LOG = LoggerFactory.getLogger(ToppingController.class);
	@Autowired
	PizzaOrderService pizzaOrderService;
	
	@Autowired
	BillService billService;
	
	@Autowired
	PizzaService pizzaService;
	
//	@RequestMapping(value = "/pizzaorder", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
//	@ResponseBody
//	public Integer addPizzaOrder(@RequestBody PizzaOrderDTO pizzaOrderDTO) {
//		Bill bill = billService.getBillById(pizzaOrderDTO.getBillId());
//		Pizza pizza = pizzaService.getPizzaById(pizzaOrderDTO.getPizzaId());
//		return pizzaOrderService.createPizzaOrder(pizzaOrderDTO.getPrice(), pizzaOrderDTO.getQuantity(), pizzaOrderDTO.getTotal(), bill, pizza);
//	}
	
	@RequestMapping(value = "/pizzaorder/order/{oId}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<PizzaOrder> gettopping(@PathVariable int oId){
        return pizzaOrderService.getPizzaOrderByBillId(oId);
    }

}
