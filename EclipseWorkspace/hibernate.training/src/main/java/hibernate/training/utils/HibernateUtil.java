package hibernate.training.utils;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import hibernate.training.entity.Brand;
import hibernate.training.entity.Category;
import hibernate.training.entity.Customer;
import hibernate.training.entity.Product;

public final class HibernateUtil {
	private HibernateUtil() {
	}

	// to be made as singleton
	private static SessionFactory factory;

	public static SessionFactory getSessionFactory() {

		if (factory == null) {
			Configuration cfg = new Configuration();
			// cfg.configure(); // reads config info from hibernate.cfg.xml
			Properties props = new Properties();
			props.setProperty("hibernate.connection.url", "jdbc:h2:tcp://localhost/~/2018_10_UNISYS_HIBERNATE");
			props.setProperty("hibernate.connection.driver", "org.h2.Driver");
			props.setProperty("hibernate.connection.username", "sa");
			props.setProperty("hibernate.connection.password", "");
			props.setProperty("hibernate.show_sql", "false");
			props.setProperty("hibernate.format_sql", "true");
			props.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
			
			
			ServiceRegistry registry = new StandardServiceRegistryBuilder()
				.applySettings(props).build();
			
			cfg.addAnnotatedClass(Brand.class);
			cfg.addAnnotatedClass(Category.class);
			cfg.addAnnotatedClass(Product.class);
			cfg.addAnnotatedClass(Customer.class);
			
			factory = cfg.buildSessionFactory(registry);
		}

		return factory;
	}
}
