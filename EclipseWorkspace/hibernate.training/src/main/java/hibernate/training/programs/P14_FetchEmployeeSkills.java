package hibernate.training.programs;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import hibernate.training.entity.Employee;
import hibernate.training.entity.TechnicalSkill;
import hibernate.training.utils.HibernateUtil;

public class P14_FetchEmployeeSkills {
	public static void main(String[] args) {

		SessionFactory factory = HibernateUtil.getSessionFactory();
		try {

			Session session = factory.openSession();

			Employee e1 = (Employee) session.get(Employee.class, 3);
			System.out.println(e1.getName() + " has the following technical skills: ");
			for (TechnicalSkill ts : e1.getSkills()) {
				System.out.println(ts.getName());
			}
			System.out.println("-----------");

			TechnicalSkill ts1 = (TechnicalSkill) session.get(TechnicalSkill.class, 1);
			System.out.println("Following employees know " + ts1.getName());
			for (Employee e : ts1.getEmployees()) {
				System.out.println(e.getName());
			}
			session.close();

		} finally {
			factory.close();
		}

	}
}
