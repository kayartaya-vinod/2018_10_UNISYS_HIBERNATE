package hibernate.training.programs;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import hibernate.training.entity.Brand;
import hibernate.training.utils.HibernateUtil;

public class P02_AddNewBrand {

	public static void main(String[] args) {
		
		SessionFactory factory = HibernateUtil.getSessionFactory();
		try {
			Session session = factory.openSession();
			Transaction tx = session.beginTransaction();
			
			Brand b1 = new Brand();
			b1.setName("Neelgiries");
			
			System.out.println("Before persist, b1.id = " + b1.getId());
			session.persist(b1); // session.save(b1);
			System.out.println("After persist, b1.id = " + b1.getId());
			
			// Session is a container (cache) of entity objects.
			// Based on the operations performed on these entities, 
			// they are categorized as "new", "dirty", "deleted" entities.
			// At the time of commit, appropriate SQL commands are
			// generated and sent to the DB. If none of the SQL commands
			// result in error, they will be committed.
			tx.commit(); 
			
			session.close();
		} finally {
			factory.close();
		}
		
	}
	
}
