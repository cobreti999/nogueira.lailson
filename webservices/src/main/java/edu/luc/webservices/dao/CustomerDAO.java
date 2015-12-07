package edu.luc.webservices.dao;

import edu.luc.webservices.helpers.TestHelper;
import edu.luc.webservices.model.Customer;

import java.sql.ResultSet;
import java.util.Collections;
import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CustomerDAO {
	public static Customer findByName(String name) {
		try {
			SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory(); 
			Session session = factory.getCurrentSession();
			session.beginTransaction();
			String queryString = "FROM Customer where customer_name = '" + name + "' ORDER BY Customer_ID" ;
			Query query = session.createQuery(queryString);
			List<Customer> results = query.list();
			return results.get(0);
		} catch (NoResultException e) {
			return (Customer) Collections.emptyList();
		}
	}
	
	public static List<Customer> findAllCustomers() {
		try {
			SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory(); 
			Session session = factory.getCurrentSession();
			session.beginTransaction();
			String queryString = "from Customer";
			Query query = session.createQuery(queryString);
			List<Customer> results = query.list();
			return results;
		} catch (NoResultException e) {
			return Collections.emptyList();
		}
	}	

	public static boolean validateUserAuthentication(String name, String password) {
		String query = "SELECT * FROM customer where customer_name = '" + name + "' AND customer_credentials = '" + password + "' ORDER BY Customer_ID DESC LIMIT 1;";
		ResultSet rs = TestHelper.makeQuery(query);
		if (rs == null)
			return false;
		else 
			return true;
	}
}
