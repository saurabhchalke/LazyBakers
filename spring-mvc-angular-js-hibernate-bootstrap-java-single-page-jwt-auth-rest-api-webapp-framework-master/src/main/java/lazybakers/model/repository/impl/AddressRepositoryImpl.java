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

import lazybakers.model.entity.Address;
import lazybakers.model.entity.User;
import lazybakers.model.repository.AddressRepository;

@Repository
public class AddressRepositoryImpl implements AddressRepository {
	
	private static Logger log = LoggerFactory.getLogger(AddressRepositoryImpl.class);
	
	@Autowired
	HibernateUtil hibernateUtil;

	@Override
	public Integer createAddress(int houseNo, String street, String city, String state, String pincode, User user) {
		SessionFactory sf = hibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		Transaction tx = null;
		Integer addressId = null;
		try {
	         tx = session.beginTransaction();
	         //log.info(user.get)
	         Address address = new Address(houseNo, street, city, state, pincode, user);
	         addressId = (Integer) session.save(address); 
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         log.error("Unable to insert record into the database");
	      } finally {
	         session.close(); 
	      }
		return addressId;
	}

	@Override
	public Address getAddressById(int addressId) {
		SessionFactory sf = hibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		Transaction tx = null;
		Address address = null;
		try {
			tx = session.beginTransaction();
	        address = (Address)session.get(Address.class, addressId);
	        tx.commit();
		} catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         log.error("Unable to get the record from the database");
	    } finally {
	         session.close(); 
	    }
		return address;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Address> getAddressByUserId(int userId) {
		SessionFactory sf = hibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		Transaction tx = null;
		List<Address> list = null;
		try {
			tx = session.beginTransaction();
			list =  session.createQuery("from address where user_id = :userId").list();
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
