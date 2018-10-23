package hibernate.training.programs;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import hibernate.training.entity.Category;
import hibernate.training.entity.Product;
import hibernate.training.utils.HibernateUtil;

public class P07_GetCategoryAndProducts {

	public static void main(String[] args) {

		SessionFactory factory = HibernateUtil.getSessionFactory();
		try {

			Session session = factory.openSession();

			int id = 2;
			Category c1 = (Category) session.get(Category.class, id);

			System.out.println("Category name is " + c1.getName());
			System.out.println("There are " + c1.getProducts().size() + " products.");
			for (Product p1 : c1.getProducts()) {
				System.out.println(p1.getDescription() + " --> " + p1.getUnitPrice());
			}
			session.close();

		} finally {
			factory.close();
		}

	}
}
