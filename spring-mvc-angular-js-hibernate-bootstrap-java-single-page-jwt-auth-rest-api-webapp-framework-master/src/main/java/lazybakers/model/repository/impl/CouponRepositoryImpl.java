package lazybakers.model.repository.impl;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import lazybakers.model.entity.Coupon;
import lazybakers.model.repository.CouponRepository;

@Repository
public class CouponRepositoryImpl implements CouponRepository {

	private static Logger log = LoggerFactory.getLogger(CouponRepositoryImpl.class);
	
	@Autowired
	HibernateUtil hibernateUtil;
	
	@Override
	public void createCoupon(String couponCode, float offerPerc, float maxAmount) {
		SessionFactory sf = hibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		Transaction tx = null;
		try {
	         tx = session.beginTransaction();
	         Coupon coupon = new Coupon(couponCode, offerPerc, maxAmount);
	         session.save(coupon); 
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         log.error("Unable to insert record into the datacoupon");
	      } finally {
	         session.close(); 
	      }
	}

	@Override
	public Coupon getCouponByCode(String couponCode) {
		SessionFactory sf = hibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		Transaction tx = null;
		Coupon coupon = null;
		try {
			tx = session.beginTransaction();
	        coupon = (Coupon)session.get(Coupon.class, couponCode);
	        tx.commit();
		} catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         log.error("Unable to get the record from the database");
	    } finally {
	         session.close(); 
	    }
		return coupon;
	}

}
