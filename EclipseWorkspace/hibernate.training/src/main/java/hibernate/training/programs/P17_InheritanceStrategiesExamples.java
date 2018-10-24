package hibernate.training.programs;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import hibernate.training.entity.Professor;
import hibernate.training.entity.Student;
import hibernate.training.utils.HibernateUtil;

public class P17_InheritanceStrategiesExamples {
	public static void main(String[] args) {
		
		SessionFactory factory = HibernateUtil.getSessionFactory();
		try {
			
			Session session = factory.openSession();
			Transaction tx = session.beginTransaction();
			
			Student st = new Student();
			st.setId(1);
			st.setName("Vishal");
			st.setSubject("MATHEMETICS");
			st.setGpa(4.5);
			
			Professor pr = new Professor();
			pr.setId(2);
			pr.setName("Venkat");
			pr.setEmail("venkat@example.com");
			pr.setDepartment("PHYSICS");
			
			session.persist(st);
			session.persist(pr);
			
			tx.commit();
			session.close();
			
		} finally {
			factory.close();
		}
		
		
	}
}
