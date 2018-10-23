package hibernate.training.programs;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import hibernate.training.entity.Brand;
import hibernate.training.utils.HibernateUtil;

public class P04_DeleteOneBrand {
	public static void main(String[] args) {
		
		SessionFactory factory = HibernateUtil.getSessionFactory();
		try {
			
			Session session = factory.openSession();
			Transaction tx = session.beginTransaction();
			
			Brand b1 = (Brand) session.get(Brand.class, 6);
			
			if(b1!=null) {
				session.delete(b1); // marked as "deleted"; and during commit, SQL DELETE is sent
				System.out.println("Brand " + b1.getName() + " deleted!");
			}
			else {
				System.out.println("No brand found with id 6");
			}
			
			tx.commit();
			session.close();
			
		} finally {
			factory.close();
		}
		
		
	}
}
