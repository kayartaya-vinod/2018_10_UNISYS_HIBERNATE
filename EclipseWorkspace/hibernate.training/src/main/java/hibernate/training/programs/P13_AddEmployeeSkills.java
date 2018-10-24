package hibernate.training.programs;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import hibernate.training.entity.Employee;
import hibernate.training.entity.TechnicalSkill;
import hibernate.training.utils.HibernateUtil;

public class P13_AddEmployeeSkills {
	public static void main(String[] args) {
		
		SessionFactory factory = HibernateUtil.getSessionFactory();
		try {
			
			Session session = factory.openSession();
			Transaction tx = session.beginTransaction();
			
			// persistent objects
			Employee e1  = (Employee) session.get(Employee.class, 1);
			Employee e2  = (Employee) session.get(Employee.class, 3);
			Employee e3  = (Employee) session.get(Employee.class, 4);
			
			// transient objects
			TechnicalSkill ts1 = new TechnicalSkill("Java");
			TechnicalSkill ts2 = new TechnicalSkill("Hibernate");
			TechnicalSkill ts3 = new TechnicalSkill("Spring");
			TechnicalSkill ts4 = new TechnicalSkill("MySQL");
			
			// helper function does bidirectional association
			ts1.addEmployee(e1);
			ts1.addEmployee(e2);
			ts1.addEmployee(e3);
			
			ts2.addEmployee(e2);
			
			ts3.addEmployee(e2);
			ts3.addEmployee(e3);
			
			ts4.addEmployee(e2);
			
			session.persist(ts1);
			session.persist(ts2);
			session.persist(ts3);
			session.persist(ts4);
			
			
			// changes to the persistent objects will be synchronized with the db
			// by firing appropriate SQL commands. Because of cascade settings,
			// the related transient objects also will be persisted
			tx.commit();
			session.close();
			
			System.out.println("Done!");
		} finally {
			factory.close();
		}
		
		
	}
}
