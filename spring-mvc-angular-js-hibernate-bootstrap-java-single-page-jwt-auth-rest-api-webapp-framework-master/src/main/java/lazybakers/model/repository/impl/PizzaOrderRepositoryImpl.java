package lazybakers.model.repository.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import lazybakers.model.entity.PizzaOrder;
import lazybakers.model.entity.Bill;
import lazybakers.model.entity.Pizza;
import lazybakers.model.repository.PizzaOrderRepository;

@Repository
public class PizzaOrderRepositoryImpl implements PizzaOrderRepository {
	
	private static Logger log = LoggerFactory.getLogger(AddressRepositoryImpl.class);
	
	@Autowired
	HibernateUtil hibernateUtil;

	@Override
	public Integer createPizzaOrder(float price, int quantity, float total, Bill bill, Pizza pizza) {
		SessionFactory sf = hibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		Transaction tx = null;
		Integer pizzaOrderId = null;
		try {
	         tx = session.beginTransaction();
	         PizzaOrder pizzaOrder = new PizzaOrder(price, quantity, total, bill, pizza);
	         pizzaOrderId = (Integer) session.save(pizzaOrder); 
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         log.error("Unable to insert record into the database");
	      } finally {
	         session.close(); 
	      }
		return pizzaOrderId;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PizzaOrder> getPizzaOrderByBillId(int billId) {
		SessionFactory sf = hibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		Transaction tx = null;
		List<PizzaOrder> list = null;
		try {
			tx = session.beginTransaction();
			list =  (List<PizzaOrder>) session.get(PizzaOrder.class, billId);
	        tx.commit();
		} catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         log.error("Unable to get the records from the database");
	    } finally {
	         session.close(); 
	    }
		return list;
	}

}
