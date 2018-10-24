package hibernate.training.programs;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import hibernate.training.entity.LineItem;
import hibernate.training.entity.Order;
import hibernate.training.entity.Product;
import hibernate.training.utils.HibernateUtil;

public class P11_FetchLineItem {
	public static void main(String[] args) {
		
		SessionFactory factory = HibernateUtil.getSessionFactory();
		try {
			
			Session session = factory.openSession();
			Order order = (Order) session.get(Order.class, 1);
			Product product = (Product) session.get(Product.class, 8);
			
			LineItem key = new LineItem();
			key.setOrder(order);
			key.setProduct(product);
			
			LineItem li = (LineItem) session.get(LineItem.class, key);
			System.out.println("Quantity   = " + li.getQuantity());
			System.out.println("Unit price = Rs. " + li.getUnitPrice());
			System.out.println("Product is " + li.getProduct().getDescription());
			System.out.println("Customer who bought this product is " + 
					li.getOrder().getCustomer().getName());
			session.close();
			
		} finally {
			factory.close();
		}
		
		
	}
}
