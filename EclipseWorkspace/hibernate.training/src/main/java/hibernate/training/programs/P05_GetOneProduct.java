package hibernate.training.programs;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import hibernate.training.entity.Product;
import hibernate.training.utils.HibernateUtil;

public class P05_GetOneProduct {

	public static void main(String[] args) {
		
		SessionFactory factory = HibernateUtil.getSessionFactory();
		try {
			
			Session session = factory.openSession();
			
			Product p1 = (Product) session.get(Product.class, 6); // get --> eager fetch
			
			session.close();
			
			// fetching a Product instance also fetches the corresponding
			// Brand and Category instances (ManyToOne is eager by default)

			System.out.println("Product name = " + p1.getName());
			System.out.println("Description  = " + p1.getDescription());
			System.out.println("Price        = Rs." + p1.getUnitPrice());
			System.out.println("Brand        = " + p1.getBrand().getName());
			System.out.println("Category     = " + p1.getCategory().getName());
			
		} finally {
			factory.close();
		}
		
	}
}
