package hibernate.training.programs;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import hibernate.training.entity.Brand;
import hibernate.training.entity.Customer;
import hibernate.training.entity.Product;
import hibernate.training.utils.HibernateUtil;

@SuppressWarnings("unchecked")
public class P16_CriteriaExamples {

	private static Session session;

	public static void main(String[] args) {

		SessionFactory factory = HibernateUtil.getSessionFactory();
		try {
			session = factory.openSession();
			// printAllBrands();
			// printProductsByPriceRange(30.0, 40.0); // min=30, max=40
			// printProductsByPage(5); // pageNum = 5, assuming pageSize = 10
			// printCustomerNamesAndEmails();
			// printProductNameWithCategoryAndBrandNames();
			// printBrandwiseProductAggregates();
			printCustomerByEmail("vinod@vinod.co");
		} finally {
			session.close();
			factory.close();
		}
	}

	static void printCustomerByEmail(String email) {
		Criteria cr = session.createCriteria(Customer.class);
		cr.add(Restrictions.eq("email", email));
		Customer c1 = (Customer) cr.uniqueResult();

		if (c1 == null) {
			System.out.println("No customer found with email: " + email);
		} else {

			System.out.println("Name  = " + c1.getName());
			System.out.println("Phone = " + c1.getPhone());
			System.out.println("City  = " + c1.getCity());
		}
	}

	static void printBrandwiseProductAggregates() {
		Criteria cr = session.createCriteria(Product.class);
		cr.createAlias("brand", "b");
		ProjectionList plist = Projections.projectionList();
		plist.add(Projections.groupProperty("b.name"));
		plist.add(Projections.rowCount());
		plist.add(Projections.avg("unitPrice"));
		plist.add(Projections.min("unitPrice"));
		plist.add(Projections.max("unitPrice"));

		cr.setProjection(plist);
		List<Object[]> list = cr.list();

		for (Object[] data : list) {
			System.out.println("Brand         : " + data[0]);
			System.out.println("Product count : " + data[1]);
			System.out.println("Avg price     : " + data[2]);
			System.out.println("Min price     : " + data[3]);
			System.out.println("Max price     : " + data[4]);
			System.out.println();
		}
	}

	static void printProductNameWithCategoryAndBrandNames() {
		Criteria cr = session.createCriteria(Product.class);
		cr.createAlias("brand", "b"); // ManyToOne or OneToOne association member
		cr.createAlias("category", "c"); // ManyToOne or OneToOne association member

		ProjectionList plist = Projections.projectionList();
		plist.add(Projections.property("name"));
		plist.add(Projections.property("b.name"));
		plist.add(Projections.property("c.name"));

		cr.setProjection(plist);
		List<Object[]> list = cr.list();

		for (Object[] data : list) {
			System.out.println(data[0] + " --> " + data[1] + " " + data[2]);
		}
	}

	static void printCustomerNamesAndEmails() {
		Criteria cr = session.createCriteria(Customer.class);
		ProjectionList plist = Projections.projectionList();
		plist.add(Projections.property("name"));
		plist.add(Projections.property("email"));
		plist.add(Projections.property("contactInfo.phone"));

		cr.setProjection(plist);
		List<Object[]> list = cr.list();

		for (Object[] data : list) {
			System.out.println(data[0] + " --> " + data[1] + " " + data[2]);
		}

	}

	static void printProductsByPage(int pageNum) {
		int pageSize = 10;
		int offset = (pageNum - 1) * pageSize;
		Criteria cr = session.createCriteria(Product.class);
		cr.setMaxResults(pageSize);
		cr.setFirstResult(offset);
		List<Product> list = cr.list();

		for (Product p : list) {
			System.out.printf("%2d %-20s Rs.%.2f\n", p.getId(), p.getName(), p.getUnitPrice());
		}
	}

	static void printProductsByPriceRange(double min, double max) {
		Criteria cr = session.createCriteria(Product.class);
		cr.add(Restrictions.ge("unitPrice", min));
		cr.add(Restrictions.le("unitPrice", max));
		cr.addOrder(Order.desc("unitPrice"));

		List<Product> list = cr.list();
		for (Product p : list) {
			System.out.println(p.getName() + " --> Rs." + p.getUnitPrice());
		}
	}

	static void printAllBrands() {
		Criteria cr = session.createCriteria(Brand.class);
		cr.addOrder(Order.asc("name"));

		List<Brand> list = cr.list();
		for (Brand b : list) {
			System.out.println(b.getId() + " --> " + b.getName());
		}
	}
}
