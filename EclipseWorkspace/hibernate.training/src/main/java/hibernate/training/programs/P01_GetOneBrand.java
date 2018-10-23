package hibernate.training.programs;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import hibernate.training.entity.Brand;
import hibernate.training.utils.HibernateUtil;

public class P01_GetOneBrand {

	public static void main(String[] args) {
		
		int id = 2; // brand id
		SessionFactory factory = HibernateUtil.getSessionFactory();
		
		try {
			Session session = factory.openSession(); // provides CRUD operations
			Brand b1 = (Brand) session.get(Brand.class, id);
			session.close(); // closes the underlying DB connection
			
			System.out.println("Brand for id " + id + " is " + b1.getName());
			
		} finally {
			factory.close(); // if not, JVM won't quit (because of registry)
		}
		
	}
}
