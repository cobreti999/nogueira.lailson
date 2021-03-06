package edu.luc.webservices.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import edu.luc.webservices.helpers.TestHelper;
import edu.luc.webservices.model.Customer;
import edu.luc.webservices.model.CustomerPayment;
import edu.luc.webservices.model.Order;

public class OrderDAO {
	public static SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory(); 
	public static Session session = factory.getCurrentSession();
	
	public static Order findOrderByCustomerName(String customerName) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory(); 
		Session session = factory.getCurrentSession();
		try {
			session.beginTransaction();
			String queryString = "FROM Customer where customerName = '" + customerName + "'";
			Query query = session.createQuery(queryString);
			List<Customer> results = query.list();
			System.out.println("test 1");
			if (results.size() > 0){
				Customer c = (Customer) results.get(0);
				System.out.println("test 2: " + c.getCustomerName());
				int customerId = c.getCustomerId();
				queryString = "from Order where Customer_FK = '" + customerId + "'";
				query = session.createQuery(queryString);
				List<Order> order = query.list();
				if (order.size() > 0 ){
					System.out.println("test 3: " + order.get(0));
					return order.get(0);
				}
				session.getTransaction().commit();
				session.close();
			}
			session.getTransaction().commit();
			session.close();
		} catch (NoResultException e) {
			return (Order) Collections.emptyList();
		}
		finally{
			session.close();
		}
		return null;
	}
	
	public static Order findOrderById(Short orderID, String customerName){
		try {
			//SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory(); 
			//Session session = factory.getCurrentSession();
			session.beginTransaction();
			String queryString = "select customer from Order where Order_Id = '" + orderID + "'";
			Query query = session.createQuery(queryString);
			Customer c = (Customer) query.list().get(0);
			int customerId = c.getCustomerId();
			queryString = "from Order where Customer_FK = '" + customerId + "'";
			query = session.createQuery(queryString);
			return (Order) query.list().get(0);
		} catch (NoResultException e) {
			return (Order) Collections.emptyList();
		}
	}
	
	public static List<Order> findAllOrders() {
		session.beginTransaction();
		String queryString = "from Order";
		Query query = session.createQuery(queryString);
		List<Order> results = query.list();
		return results;
	}	
}
