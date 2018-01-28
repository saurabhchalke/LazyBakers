package lazybakers.model.repository.impl;

import java.util.Date;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import lazybakers.model.entity.Address;
import lazybakers.model.entity.Bill;
import lazybakers.model.entity.Coupon;
import lazybakers.model.entity.User;
import lazybakers.model.repository.BillRepository;

@Repository
public class BillRepositoryImpl implements BillRepository {
	
	private static Logger log = LoggerFactory.getLogger(BillRepositoryImpl.class);
	
	@Autowired
	HibernateUtil hibernateUtil;

	@Override
	public Integer createBill(Date billDate, int totalItems, float billAmount, float tax, String stage, int feedback, String comments,
			String offerType, Coupon coupon, Address address, User user) {
		SessionFactory sf = hibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		Transaction tx = null;
		Integer billId = null;
		try {
	         tx = session.beginTransaction();
	         Bill bill = new Bill(billDate, totalItems, billAmount, tax, stage, feedback, comments, offerType, coupon, address, user);
	         billId = (Integer) session.save(bill); 
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         log.error("Unable to insert record into the database");
	      } finally {
	         session.close(); 
	      }
		return billId;
	}

	@Override
	public Bill getBillById(int billId) {
		SessionFactory sf = hibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		Transaction tx = null;
		Bill bill = null;
		try {
			tx = session.beginTransaction();
	        bill = (Bill)session.get(Bill.class, billId);
	        tx.commit();
		} catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         log.error("Unable to get the record from the database");
	    } finally {
	         session.close(); 
	    }
		return bill;
	}

}
