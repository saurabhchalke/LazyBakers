package lazybakers.service;

import java.util.List;

import lazybakers.model.entity.Bill;
import lazybakers.model.entity.Pizza;
import lazybakers.model.entity.PizzaOrder;

public interface PizzaOrderService {
	
	public Integer createPizzaOrder(float price, int quantity, float total, Bill bill, Pizza pizza);
	
	public List<PizzaOrder> getPizzaOrderByBillId(int billId);

}
