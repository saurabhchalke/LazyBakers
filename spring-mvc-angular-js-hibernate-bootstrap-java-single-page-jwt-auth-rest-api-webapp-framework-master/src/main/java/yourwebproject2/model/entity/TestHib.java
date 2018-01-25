package yourwebproject2.model.entity;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;

public class TestHib {

	public static void main(String[] args) {

		Customer customer = new Customer("Saurabh", "Chalke", "8050", 11, "s@s.s");
		
		Address address = new Address(132, "SM", "BLR", "KAR", "560065", customer);
		Address address2 = new Address(133, "S2", "BLR", "KAR", "560065", customer);
		
		Configuration con = new Configuration().configure().addAnnotatedClass(Customer.class).addAnnotatedClass(Address.class);
		
		SessionFactory sf = con.buildSessionFactory();
        
        Session session1 = sf.openSession();
        
        Transaction tx = session1.beginTransaction();
        session1.save(customer);
        session1.save(address);
        session1.save(address2);
        tx.commit();
        session1.close();
		
		
	}

}
