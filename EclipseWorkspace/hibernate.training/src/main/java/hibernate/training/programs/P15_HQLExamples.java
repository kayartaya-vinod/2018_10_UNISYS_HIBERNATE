package hibernate.training.programs;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import hibernate.training.entity.Brand;
import hibernate.training.entity.Category;
import hibernate.training.entity.Customer;
import hibernate.training.entity.Product;
import hibernate.training.utils.HibernateUtil;

@SuppressWarnings("unchecked")
public class P15_HQLExamples {
	
	private static Session session;
	
	public static void main(String[] args) {
		
		SessionFactory factory = HibernateUtil.getSessionFactory();
		try {
			session = factory.openSession();
			
			// printAllBrands();
			// printAllCategories();
			// printProductsByPriceRange(30.0, 40.0); //  min=30, max=40
			// printProductsByPage(5); // pageNum = 5, assuming pageSize = 10
			// printCustomerNames(); // not entire customer objects, but only names
			// printCustomerNamesAndEmails(); 
			// printBrandwiseProductAggregates();
			// printOrderTotalDetails();
			// incrmentProductPriceBy(1.5); // increase unit_price by Rs.1.5
			printCustomerByEmail("vinod@vinod.co");
			
		} finally {
			
			session.close();
			factory.close();
		}
	}

	static void printCustomerByEmail(String email) {
		String hql = "from Customer where email = :EMAIL";
		Query qry = session.createQuery(hql);
		qry.setString("EMAIL", email);
		Customer c1 = (Customer) qry.uniqueResult();
		
		if (c1 == null) {
			System.out.println("No customer found with email: " + email);
		} else {

			System.out.println("Name  = " + c1.getName());
			System.out.println("Phone = " + c1.getPhone());
			System.out.println("City  = " + c1.getCity());
		}
	}

	static void incrmentProductPriceBy(double incPrice) {
		String hql = "update Product set unitPrice = unitPrice + ?"; // ? parameters start from index 0
		Query qry = session.createQuery(hql);
		qry.setDouble(0, incPrice);
		int rowCount = qry.executeUpdate(); // for UPDATE/DELETE sql commands
		session.beginTransaction().commit();
		System.out.println(rowCount + " rows updated!");
	}

	static void printOrderTotalDetails() {
		String sql = "select \n" + 
				"o.id, c.name, c.phone, sum(li.quantity*li.unit_price), o.status\n" + 
				"from orders o\n" + 
				"join customers c\n" + 
				"on o.customer_id = c.id\n" + 
				"join line_items li\n" + 
				"on o.id = li.order_id\n" + 
				"group by o.id, c.name, c.phone order by o.id";
		
		// use SQLQuery when 
		// 1) the query refers to tables which are not mapped to any entity classes
		// 2) the query refers to native SQL functions
		// 3) the query is very complex and is difficult to be represented as HQL
		SQLQuery qry = session.createSQLQuery(sql);
		List<Object[]> list = qry.list(); // does not convert to SQL (since we are supplying SQL itself)
		
		for (Object[] data : list) {
			System.out.println("Order id: " + data[0]);
			System.out.println("Customer: " + data[1] + " " + data[2]);
			System.out.println("Total   : Rs." + data[3]);
			System.out.println("Status  : " + data[4]);
			System.out.println();
		}
	}

	static void printBrandwiseProductAggregates() {
		String hql = "select p.brand.name, count(p), avg(p.unitPrice) from Product p group by p.brand";
		Query qry = session.createQuery(hql);
		List<Object[]> list = qry.list();
		for (Object[] data : list) {
			System.out.printf("%-20s %2d %10.2f\n",
					data[0], data[1], data[2]);
		}
	}

	static void printCustomerNamesAndEmails() {
		// projection
		String hql = "select name, email, contactInfo.phone from Customer";
		Query qry = session.createQuery(hql);
		List<Object[]> list = qry.list();
		for (Object[] cust : list) {
			String name = (String) cust[0];
			String email = (String) cust[1];
			String phone = (String) cust[2];
			System.out.println(name + " --> " + email + ", " + phone);
		}
	}

	static void printCustomerNames() {
		String hql = "select name from Customer";
		Query qry = session.createQuery(hql);
		List<String> names = qry.list();
		for(String name: names) {
			System.out.println(name);
		}
	}

	static void printProductsByPage(int pageNum) {
		int pageSize = 10;
		int offset = (pageNum-1) * pageSize;
		
		Query qry = session.createQuery("from Product");
		qry.setMaxResults(pageSize); // pageSize records per page
		qry.setFirstResult(offset); // skil the first "offset" number of rows
		List<Product> list = qry.list();
		for (Product p : list) {
			System.out.printf("%2d %s (Rs.%.2f)\n", p.getId(), p.getName(), p.getUnitPrice());
		}
	}

	static void printProductsByPriceRange(double min, double max) {
		String hql = "from Product where unitPrice between :MIN_PRICE and :MAX_PRICE "
				+ "order by unitPrice desc";
		Query qry = session.createQuery(hql);
		qry.setDouble("MIN_PRICE", min);
		qry.setDouble("MAX_PRICE", max);
		List<Product> list = qry.list();
		for (Product p : list) {
			System.out.printf("%s --> Rs.%.2f\n", p.getName(), p.getUnitPrice());
		}
	}

	static void printAllCategories() {
		Query qry = session.createQuery("FROM hibernate.training.entity.Category ORDER BY name");
		List<Category> list = qry.list();
		for(Category c: list) {
			System.out.printf("%d --> %s (%d products)\n", 
					c.getId(), c.getName(), c.getProducts().size());
		}
	}

	static void printAllBrands() {
		// sql --> select * from brands order by name
		// hql --> select b from Brand b order by b.name
		String hql = "select b from Brand b order by b.id";
		// org.hibernate.Session provides only CRUD operations (based on PK value)
		// org.hibernate.Query allows us to execute HQL
		Query qry = session.createQuery(hql);
		List<Brand> list = qry.list(); // HQL is converted into SQL and is fired to the DB
		
		for(Brand b: list) {
			System.out.printf("%2d  --> %s (%d products)\n", 
					b.getId(), b.getName(), b.getProducts().size());
		}
	}

}
