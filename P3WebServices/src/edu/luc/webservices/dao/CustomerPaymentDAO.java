package edu.luc.webservices.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import edu.luc.webservices.helpers.TestHelper;
import edu.luc.webservices.model.Customer;
import edu.luc.webservices.model.CustomerPayment;
import edu.luc.webservices.model.Product;

public class CustomerPaymentDAO {
	
	public static CustomerPayment findCustomerPaymentByCustomerName(String customerName){
		try {
			SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory(); 
			Session session = factory.getCurrentSession();
			session.beginTransaction();
			Customer customer = CustomerDAO.findByName(customerName);
			//System.out.println(customer.getCustomerName());
			String queryString = "FROM CustomerPayment where customer_fk = '" + customer.getCustomerId() + "'";
			Query query = session.createQuery(queryString);
			return (CustomerPayment) query.list().get(0);
		} catch (NoResultException e) {
			return (CustomerPayment) Collections.emptyList();
		}
	}
	
	
	public CustomerPayment findPaymentByOrderId(Short orderId){
		try {
			SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory(); 
			Session session = factory.getCurrentSession();
			session.beginTransaction();
			String queryString = "select customer from Order where Order_Id = '" + orderId + "'";
			/*String queryString = "select b.* from orders a, CustomerPayment b where a.payment_fk = "
					+ "b.customer_payment_id and order_id = " + orderId;*/
			Query query = session.createQuery(queryString);
			Customer c = (Customer) query.list().get(0);
			int customerId = c.getCustomerId();
			queryString = "from CustomerPayment where Customer_FK = '" + customerId + "'";
			query = session.createQuery(queryString);
			return (CustomerPayment) query.list().get(0);
		} catch (NoResultException e) {
			return (CustomerPayment) Collections.emptyList();
		}
	}
	
	public CustomerPayment findPaymentByCustomerId(Short customerId){
		try {
			SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory(); 
			Session session = factory.getCurrentSession();
			session.beginTransaction();
			/*String queryString = "select b.* from customer a, CustomerPayment b where b.customer_fk = "
			+ "a.customer_id and customer_id = " + orderId;*/
			String queryString = "from CustomerPayment where Customer_FK = '" + customerId + "'";
			Query query = session.createQuery(queryString);
			return (CustomerPayment) query.list().get(0);
		} catch (NoResultException e) {
			return (CustomerPayment) Collections.emptyList();
		}
	}
	
	public List<CustomerPayment> findAllPayments() {
		try {
			SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory(); 
			Session session = factory.getCurrentSession();
			session.beginTransaction();
			String queryString = "from CustomerPayment";
			Query query = session.createQuery(queryString);
			List<CustomerPayment> results = query.list();
			return results;
		} catch (NoResultException e) {
			return Collections.emptyList();
		}
	}	
}
