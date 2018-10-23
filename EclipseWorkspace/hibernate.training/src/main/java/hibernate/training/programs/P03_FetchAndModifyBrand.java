package hibernate.training.programs;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import hibernate.training.entity.Brand;
import hibernate.training.utils.HibernateUtil;

public class P03_FetchAndModifyBrand {

	public static void main(String[] args) {
		
		SessionFactory factory = HibernateUtil.getSessionFactory();
		try {
			
			Session session = factory.openSession();
			Transaction tx = session.beginTransaction();
			
			// at this time, session is empty!
			Brand b1 = (Brand) session.get(Brand.class, 4);
			// at this time, session contains 1 object 
			
			
			Brand b2 = new Brand();
			b2.setId(5);
			b2.setName("GreensVeg");
			// at this time JVM has 2 brand objects, session has 1
			
			// From hibernate's perspective, b1 is called "persistent" object
			// and b2 is called "transient" object
			
			// If a persistent object is modifed, session will mark the same as "dirty"
			// and tx.commit() will send an SQL UPDATE corresponding to the same.
			
			b1.setName("Farm fresh"); // b1 is now marked as "dirty"
			
			session.merge(b2); // SELECT and conditionally UPDATE
			// NOW, session contains 2 objects (b1 and b2 are persistent)
			
			tx.commit();
			session.close();
			
			// NOW, b1 and b2 both of them are disconnected from the session
			// since session is closed and are called "detached" objects
			
			
		} finally {
			factory.close();
		}
		
		
	}
	
}
