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

import lazybakers.model.entity.Base;
import lazybakers.model.entity.Pizza;
import lazybakers.model.repository.PizzaRepository;

@Repository
public class PizzaRepositoryImpl implements PizzaRepository {
	
	private static Logger log = LoggerFactory.getLogger(PizzaRepositoryImpl.class);
	
	@Autowired
	HibernateUtil hibernateUtil;

	@Override
	public Integer createPizza(String pizzaName, String pizzaDesc, float price, String size, boolean customized,
			Base base) {
		SessionFactory sf = hibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		Transaction tx = null;
		Integer pizzaId = null;
		try {
	         tx = session.beginTransaction();
	         Pizza pizza = new Pizza(pizzaName, pizzaDesc, price, size, customized, base);
	         pizzaId = (Integer) session.save(pizza); 
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         log.error("Unable to insert record into the database");
	      } finally {
	         session.close(); 
	      }
		return pizzaId;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Pizza> getAllPizza() {
		SessionFactory sf = hibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		Transaction tx = null;
		List<Pizza> list = null;
		try {
			tx = session.beginTransaction();
			list =  session.createCriteria(Pizza.class).list();
			tx.commit();
		} catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         log.error("Unable to get the records from the database");
	    } finally {
	         session.close(); 
	    }
		return list;
	}

	@Override
	public Pizza getPizzaById(int pizzaId) {
		SessionFactory sf = hibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		Transaction tx = null;
		Pizza pizza = null;
		try {
			tx = session.beginTransaction();
	        pizza = (Pizza)session.get(Pizza.class, pizzaId);
	        tx.commit();
		} catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         log.error("Unable to get the record from the database");
	    } finally {
	         session.close(); 
	    }
		return pizza;
	}
	
}
