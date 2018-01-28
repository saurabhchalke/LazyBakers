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
import lazybakers.model.repository.BaseRepository;

@Repository
public class BaseRepositoryImpl implements BaseRepository {
	
	private static Logger log = LoggerFactory.getLogger(BaseRepositoryImpl.class);
	
	@Autowired
	HibernateUtil hibernateUtil;

	@Override
	public Integer createBase(String baseName, float price) {
		SessionFactory sf = hibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		Transaction tx = null;
		Integer baseid = null;
		try {
	         tx = session.beginTransaction();
	         Base base = new Base(baseName, price);
	         baseid = (Integer) session.save(base); 
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         log.error("Unable to insert record into the database");
	      } finally {
	         session.close(); 
	      }
		return baseid;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Base> getAllBase() {
		SessionFactory sf = hibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		Transaction tx = null;
		List<Base> list = null;
		try {
			tx = session.beginTransaction();
			list =  session.createCriteria(Base.class).list();
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
	public Base getBaseById(int baseId) {
		SessionFactory sf = hibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		Transaction tx = null;
		Base base = null;
		try {
			tx = session.beginTransaction();
	        base = (Base)session.get(Base.class, baseId);
	        tx.commit();
		} catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         log.error("Unable to get the record from the database");
	    } finally {
	         session.close(); 
	    }
		return base;
	}

}
