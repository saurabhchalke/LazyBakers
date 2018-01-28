package lazybakers.model.repository.impl;

import lazybakers.framework.data.BaseHibernateJPARepository;
import lazybakers.model.entity.User;
import lazybakers.model.repository.UserRepository;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * User Repository Implementation
 *
 * @author: kameshr
 */
@Repository
public class UserRepositoryImpl extends BaseHibernateJPARepository<User, Long> implements UserRepository {
	private static Logger log = LoggerFactory.getLogger(UserRepositoryImpl.class);
	
	@Autowired
	HibernateUtil hibernateUtil;

    @Override
    public User findByEmail(String email) {
        return (User) sessionFactory.getCurrentSession().createQuery("from User u where u.email = :email")
                .setParameter("email", email).uniqueResult();
    }

	@Override
	public User getUserById(long userId) {
		SessionFactory sf = hibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		Transaction tx = null;
		User user = null;
		try {
			tx = session.beginTransaction();
	        user = (User)session.get(User.class, userId);
	        tx.commit();
		} catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         log.error("Unable to get the record from the database");
	    } finally {
	         session.close(); 
	    }
		return user;
	}
}
