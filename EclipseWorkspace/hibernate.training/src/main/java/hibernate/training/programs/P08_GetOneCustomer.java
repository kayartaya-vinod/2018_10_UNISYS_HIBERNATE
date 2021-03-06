package hibernate.training.programs;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import hibernate.training.entity.Customer;
import hibernate.training.utils.HibernateUtil;

public class P08_GetOneCustomer {
	public static void main(String[] args) {

		SessionFactory factory = HibernateUtil.getSessionFactory();
		try {

			Session session = factory.openSession();

			Customer c1 = (Customer) session.get(Customer.class, 1);

			System.out.println("Name    = " + c1.getName());
			System.out.println("Email   = " + c1.getEmail());
			System.out.println("Phone   = " + c1.getPhone());
			System.out.println("City    = " + c1.getCity());

			session.close();

		} finally {
			factory.close();
		}

	}
}
