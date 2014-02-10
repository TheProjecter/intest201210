package intest.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {

	private static SessionFactory sessionFactory = buildSessionFactory();

	private static ServiceRegistry serviceRegistry;

	private static SessionFactory buildSessionFactory() {
		
		try {
			
			// Create the SessionFactory from hibernate.cfg.xml
			Configuration configuration = new Configuration();
			configuration.configure();

			serviceRegistry = new StandardServiceRegistryBuilder()
					.applySettings(configuration.getProperties()).build();
			sessionFactory = configuration.buildSessionFactory(serviceRegistry);

			return sessionFactory;
			
		} catch (Throwable e) {
			
			// Make sure you log the exception, as it might be swallowed
			System.err.println("Initial SessionFactory creation failed." + e);
			throw new ExceptionInInitializerError(e);
			
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public static void main(String args[]) {
		Session session = HibernateUtil.getSessionFactory().withOptions()
				.openSession();
		System.out.println(session.isConnected());
	}

}
