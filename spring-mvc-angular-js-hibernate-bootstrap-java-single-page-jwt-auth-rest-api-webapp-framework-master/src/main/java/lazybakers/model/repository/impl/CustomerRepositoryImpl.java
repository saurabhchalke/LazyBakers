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

import lazybakers.model.entity.Customer;
import lazybakers.model.repository.CustomerRepository;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository {
	private static Logger log = LoggerFactory.getLogger(CustomerRepositoryImpl.class);
	
	@Autowired
	HibernateUtil hibernateUtil;
	
	@Override
	public Integer createCustomer(String customerFirstName, String customerLastName, String password,
			String mobilenumber, int points, String customerEmail) {
		SessionFactory sf = hibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		Transaction tx = null;
		Integer customerId = null;
		try {
	         tx = session.beginTransaction();
	         Customer customer = new Customer(customerFirstName, customerLastName, password, 
	        		 mobilenumber, points, customerEmail);
	         customerId = (Integer) session.save(customer); 
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         log.error("Unable to insert record into the database");
	      } finally {
	         session.close(); 
	      }
		return customerId;
	}

	@Override
	public void updateCustomer(int customerId, String customerFirstName, String customerLastName, String password,
			String mobilenumber, int points, String customerEmail) {
		SessionFactory sf = hibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		Transaction tx = null;
		try {
	         tx = session.beginTransaction();
	         Customer customer = (Customer)session.get(Customer.class, customerId); 
	         customer.setCustomerFirstName(customerFirstName);
	         customer.setCustomerLastName(customerLastName);
	         customer.setCustomerEmail(customerEmail);
	         customer.setPassword(password);
	         customer.setmobilenumber(mobilenumber);
	         customer.setCustomerEmail(customerEmail);
			 session.update(customer); 
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         log.error("Unable to update the record in the database");
	      } finally {
	         session.close(); 
	      }
	}

	@Override
	public void deleteCustomer(int customerId) {
		SessionFactory sf = hibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		Transaction tx = null;
		try {
	         tx = session.beginTransaction();
	         Customer customer = (Customer)session.get(Customer.class, customerId); 
	         session.delete(customer); 
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         log.error("Unable to delete the record from the database");
	      } finally {
	         session.close(); 
	      }
	}

	@Override
	public Customer getCustomerById(int customerId) {
		SessionFactory sf = hibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		Transaction tx = null;
		Customer customer = null;
		try {
			tx = session.beginTransaction();
	        customer = (Customer)session.get(Customer.class, customerId);
	        tx.commit();
		} catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         log.error("Unable to get the record from the database");
	    } finally {
	         session.close(); 
	    }
		return customer;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Customer> getAllCustomer() {
		SessionFactory sf = hibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		Transaction tx = null;
		List<Customer> list = null;
		try {
			tx = session.beginTransaction();
			list = session.createCriteria(Customer.class).list();
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
