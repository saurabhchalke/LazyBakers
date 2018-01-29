package lazybakers.controller;

import java.util.Date;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import lazybakers.model.dto.BillDTO;
import lazybakers.model.dto.PizzaOrderDTO;
import lazybakers.model.entity.Address;
import lazybakers.model.entity.Base;
import lazybakers.model.entity.Bill;
import lazybakers.model.entity.Coupon;
import lazybakers.model.entity.User;
import lazybakers.service.AddressService;
import lazybakers.service.BaseService;
import lazybakers.service.BillService;
import lazybakers.service.CouponService;
import lazybakers.service.PizzaOrderService;
import lazybakers.service.PizzaService;
import lazybakers.service.PizzaToppingService;
import lazybakers.service.ToppingService;
import lazybakers.service.UserService;

@Controller
public class BillController {
	
	@Autowired
	BaseService baseService;
	@Autowired
	BillService billService;
	@Autowired
	CouponService couponService;
	@Autowired
	AddressService addressService;
	@Autowired
	UserService userService;
	@Autowired
	PizzaService pizzaService;
	@Autowired
	PizzaOrderService pizzaOrderSerivce;
	@Autowired
	PizzaToppingService pizzaToppingService;
	@Autowired
	ToppingService toppingService;
	
	@RequestMapping(value = "/bill", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public Integer addbill(@RequestBody BillDTO billDTO) {
		Coupon coupon = couponService.getCouponByCode("test");
		Address address = addressService.getAddressById(1);
		User user = userService.getUserById(1);
		int billId =  billService.createBill(new Date(), 10, billDTO.getTotalCost(), billDTO.getTax(), "Order received", 5, "None", "None", coupon, address, user);
		Iterator<PizzaOrderDTO> it = billDTO.getItems().iterator();
		while(it.hasNext()) {
			PizzaOrderDTO tempPizzaOrderDTO = it.next();
			if(tempPizzaOrderDTO.getData() == null) {
				//Change id to integer
				pizzaOrderSerivce.createPizzaOrder(tempPizzaOrderDTO.getPrice(), tempPizzaOrderDTO.getQuantity(), tempPizzaOrderDTO.getTotal(), billService.getBillById(billId), pizzaService.getPizzaById(1));
				continue;
			}
			boolean uncustomized = false;
			if(tempPizzaOrderDTO.getId() == null)
				uncustomized = true;
			Base base = baseService.getBaseById(1);
			int pizzaId = pizzaService.createPizza(tempPizzaOrderDTO.getName(), "None", tempPizzaOrderDTO.getPrice(), tempPizzaOrderDTO.getData().getSize(), uncustomized, base);
			pizzaOrderSerivce.createPizzaOrder(tempPizzaOrderDTO.getPrice(), tempPizzaOrderDTO.getQuantity(), tempPizzaOrderDTO.getTotal(), billService.getBillById(billId), pizzaService.getPizzaById(pizzaId));
			Iterator<Integer> toppingIterator = tempPizzaOrderDTO.getData().getToppings().iterator();
			while(toppingIterator.hasNext()) {
				pizzaToppingService.createPizzaTopping(pizzaService.getPizzaById(pizzaId), toppingService.getToppingById(toppingIterator.next()));
			}
		}
		return billId;
	}
	
	@RequestMapping(value = "/bill/{bId}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Bill getbill(@PathVariable int bId){
        return billService.getBillById(bId);
    }
}
