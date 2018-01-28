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

import lazybakers.model.entity.Pizza;
import lazybakers.model.entity.PizzaTopping;
import lazybakers.model.entity.Topping;
import lazybakers.model.repository.PizzaToppingRepository;

@Repository
public class PizzaToppingRepositoryImpl implements PizzaToppingRepository {
	
	private static Logger log = LoggerFactory.getLogger(ToppingRepositoryImpl.class);
	
	@Autowired
	HibernateUtil hibernateUtil;

	@Override
	public Integer createPizzaTopping(Pizza pizza, Topping topping) {
		SessionFactory sf = hibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		Transaction tx = null;
		Integer pizzaToppingId = null;
		try {
	         tx = session.beginTransaction();
	         PizzaTopping pizzaTopping = new PizzaTopping(pizza, topping);
	         pizzaToppingId = (Integer) session.save(pizzaTopping); 
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         log.error("Unable to insert record into the database");
	      } finally {
	         session.close(); 
	      }
		return pizzaToppingId;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PizzaTopping> getPizzaToppingByPizzaId(int pizzaId) {
		SessionFactory sf = hibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		Transaction tx = null;
		List<PizzaTopping> list = null;
		try {
			tx = session.beginTransaction();
			list =  session.createQuery("from pizza_topping where pizza_pizza_id = :pizzaId").list();
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
