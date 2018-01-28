package lazybakers.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lazybakers.model.entity.Bill;
import lazybakers.model.entity.Pizza;
import lazybakers.model.entity.PizzaOrder;
import lazybakers.model.repository.PizzaOrderRepository;
import lazybakers.service.PizzaOrderService;

@Service("pizzaOrderService")
@Transactional
public class PizzaOrderServiceImpl implements PizzaOrderService {
	
	@Autowired
	PizzaOrderRepository pizzaOrderRepository;

	@Override
	public Integer createPizzaOrder(float price, int quantity, float total, Bill bill, Pizza pizza) {
		return pizzaOrderRepository.createPizzaOrder(price, quantity, total, bill, pizza);
	}

	@Override
	public List<PizzaOrder> getPizzaOrderByBillId(int billId) {
		return pizzaOrderRepository.getPizzaOrderByBillId(billId);
	}

}
