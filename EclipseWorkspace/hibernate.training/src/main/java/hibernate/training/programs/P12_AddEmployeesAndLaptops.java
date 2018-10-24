package hibernate.training.programs;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import hibernate.training.entity.Employee;
import hibernate.training.entity.Laptop;
import hibernate.training.utils.HibernateUtil;

public class P12_AddEmployeesAndLaptops {

	public static void main(String[] args) {

		SessionFactory factory = HibernateUtil.getSessionFactory();
		try {

			Session session = factory.openSession();
			Transaction tx = session.beginTransaction();

			Employee e1 = new Employee("Ramesh", 40000.);
			Employee e2 = new Employee("Suresh", 33000.);
			Employee e3 = new Employee("Harish", 37000.);
			Employee e4 = new Employee("Naresh", 49000.);
			Employee e5 = new Employee("James", 22000.);
			
			Laptop lt1 = new Laptop("AC2234", "Acer", "TM650");
			Laptop lt2 = new Laptop("AC1234", "Acer", "TX928");
			Laptop lt3 = new Laptop("DL2224", "Dell", "DXX0383");
			
			// bidirectional association
			e2.setLaptop(lt1);
			e3.setLaptop(lt2);
			e5.setLaptop(lt3);
			
			// save only employee instances (which in turn saves laptop instances)
			session.persist(e1);
			session.persist(e2);
			session.persist(e3);
			session.persist(e4);
			session.persist(e5);
			

			tx.commit();
			session.close();

			System.out.println("Done");
			
		} finally {
			factory.close();
		}

	}
}
