package hibernate.training.programs;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import hibernate.training.entity.Customer;
import hibernate.training.entity.LineItem;
import hibernate.training.entity.Order;
import hibernate.training.entity.Product;
import hibernate.training.utils.HibernateUtil;

public class P09_AddNewOrder {

	public static void main(String[] args) {
		
		SessionFactory factory = HibernateUtil.getSessionFactory();
		try {
			
			Session session = factory.openSession();
			Transaction tx = session.beginTransaction();
			
			// fetch a customer (or create a new customer)
			// Customer customer = (Customer) session.get(Customer.class, 1);
			Customer customer = new Customer();
			customer.setName("Ramesh Iyer");
			customer.setCity("Bangalore");
			customer.setCountry("India");
			customer.setState("Karnataka");
			customer.setEmail("rameshiyer@example.com");
			customer.setPhone("9876511223");
			customer.setPassword("topsecret");
			
			// fetch few products
			Product p1 = (Product) session.get(Product.class, 1);
			Product p2 = (Product) session.get(Product.class, 2);
			Product p3 = (Product) session.get(Product.class, 3);
			
			// create line items for each products fetched
			LineItem li1 = new LineItem();
			li1.setProduct(p1); // also sets the unit_price (see customized setProduct() function)
			li1.setQuantity(1);
			
			LineItem li2 = new LineItem();
			li2.setProduct(p2);
			li2.setQuantity(1);
			
			LineItem li3 = new LineItem();
			li3.setProduct(p3);
			li3.setQuantity(2);
			
			// create a new Order object
			Order order = new Order();
			
			// set the customer to the newly created Order object
			order.setCustomer(customer); // logged in customer (or newly registered one)
			
			// add all the line items to the Order object (use the helper function)
			order.addLineItem(li1);
			order.addLineItem(li2);
			order.addLineItem(li3);
			
			// save the Order object (and because cascade settings, all line items 
			// + customer info will be saved)
			session.persist(order);
			
			System.out.println("Done");
			
			tx.commit();
			session.close();
			
		} finally {
			factory.close();
		}
		
		
	}
}
