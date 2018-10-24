package hibernate.training.programs;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import hibernate.training.entity.Customer;
import hibernate.training.entity.LineItem;
import hibernate.training.entity.Order;
import hibernate.training.utils.HibernateUtil;

public class P10_FetchOrderDetails {

	public static void main(String[] args) {
		
		SessionFactory factory = HibernateUtil.getSessionFactory();
		try {
			
			Session session = factory.openSession();

			Order order = (Order) session.get(Order.class, 1);
			
			System.out.println("Order date      : " + order.getOrderDate());
			System.out.println("Order status    : " + order.getStatus());
			
			Customer c = order.getCustomer();
			System.out.println("Order placed by : " + c.getName());
			System.out.println("Phone number    : " + c.getPhone());
			System.out.println("Email address   : " + c.getEmail());
			System.out.println("Address         : " + c.getStreetAddress());
			
			double orderTotal = 0;
			
			System.out.println("------------------------------------------------------");
			System.out.println("Product                    Quantity  Amount");
			System.out.println("------------------------------------------------------");
			for(LineItem li: order.getLineItems()) {
				orderTotal += (li.getQuantity() * li.getUnitPrice());
				System.out.printf("%-30s %3d %10.2f\n",
						li.getProduct().getDescription(),
						li.getQuantity(),
						li.getQuantity()*li.getUnitPrice());
			}
			System.out.println("------------------------------------------------------");
			System.out.println("Order total     : " + orderTotal);
			
			session.close();
			
		} finally {
			factory.close();
		}
		
		
	}
	
}
