package lazybakers.model.repository.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import lazybakers.model.entity.Topping;
import lazybakers.model.repository.ToppingRepository;

@Repository
public class ToppingRepositoryImpl implements ToppingRepository {
	private static Logger log = LoggerFactory.getLogger(ToppingRepositoryImpl.class);
	
	@Autowired
	HibernateUtil hibernateUtil;

	@Override
	public Integer createTopping(String toppingname, float price, int stock, boolean vegetarian) {
		SessionFactory sf = hibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		Transaction tx = null;
		Integer toppingId = null;
		try {
	         tx = session.beginTransaction();
	         Topping topping = new Topping(toppingname, price, stock, vegetarian);
	         toppingId = (Integer) session.save(topping); 
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         log.error("Unable to insert record into the database");
	      } finally {
	         session.close(); 
	      }
		return toppingId;
	}

	@Override
	public void updateTopping(int toppingId) {
		SessionFactory sf = hibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		Transaction tx = null;
		try {
	         tx = session.beginTransaction();
	         log.info("update topping set stock = stock + 100 where topping_id = " + toppingId);
	         Query query = session.createQuery("update topping set stock = 200 where topping_id = :toppingId");
	         log.info("dsd " + query.executeUpdate());
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         log.error("Unable to update the record in the database");
	      } finally {
	         session.close(); 
	      }
		
	}

	@Override
	public void deleteTopping(int toppingId) {
		SessionFactory sf = hibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		Transaction tx = null;
		try {
	         tx = session.beginTransaction();
	         Topping topping = (Topping)session.get(Topping.class, toppingId); 
	         session.delete(topping); 
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         log.error("Unable to delete the record from the database");
	      } finally {
	         session.close(); 
	      }
	}

	@Override
	public Topping getToppingById(int toppingId) {
		SessionFactory sf = hibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		Transaction tx = null;
		Topping topping = null;
		try {
			tx = session.beginTransaction();
	        topping = (Topping)session.get(Topping.class, toppingId);
	        tx.commit();
		} catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         log.error("Unable to get the record from the database");
	    } finally {
	         session.close(); 
	    }
		return topping;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Topping> getAllTopping() {
		SessionFactory sf = hibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		Transaction tx = null;
		List<Topping> list = null;
		try {
			tx = session.beginTransaction();
			list =  session.createCriteria(Topping.class).list();
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
