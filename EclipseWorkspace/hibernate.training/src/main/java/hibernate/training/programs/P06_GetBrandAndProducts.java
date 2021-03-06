package hibernate.training.programs;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import hibernate.training.entity.Brand;
import hibernate.training.entity.Product;
import hibernate.training.utils.HibernateUtil;

public class P06_GetBrandAndProducts {

	public static void main(String[] args) {

		SessionFactory factory = HibernateUtil.getSessionFactory();
		try {

			Session session = factory.openSession();

			int brandId = 3;
			Brand b1 = (Brand) session.get(Brand.class, brandId);

			
			System.out.println("Brand name is " + b1.getName());
			System.out.println("There are " + b1.getProducts().size() + " products.");
			for (Product p1 : b1.getProducts()) {
				System.out.println(p1.getDescription() + " --> " + p1.getUnitPrice());
			}
			session.close();

		} finally {
			factory.close();
		}

	}
}
