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
		Customer customer = CustomerDAO.findByName(customerName);
		String query = "SELECT * FROM customer_payment where customer_fk = '" + customer.getCustomerId() + "' ORDER BY Customer_ID DESC LIMIT 1;";
		try {
			ResultSet rs = TestHelper.makeQuery(query);
			String customerPaymentBilling = "";
			String customerPaymentDescription = "";

			while (rs.next()){
				customerPaymentBilling =  rs.getString("Customer_Payment_Billing");
				customerPaymentDescription = rs.getString("Customer_Payment_Description");
			}
			rs.close();
			TestHelper.stmt.close();
			TestHelper.conn.close();
			CustomerPayment customerPayment = new CustomerPayment(customer, customerPaymentDescription, customerPaymentBilling);
			return customerPayment;

		} catch(SQLException se){
			//Handle errors for JDBC
			se.printStackTrace();
		}catch(Exception e){
			//Handle errors for Class.forName
			e.printStackTrace();
		}
		return null;
	}
	
	public CustomerPayment findPaymentByOrderId(Short orderId){
		try {
			SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory(); 
			Session session = factory.getCurrentSession();
			session.beginTransaction();
			String queryString = "select b.* from orders a, customer_payment b where a.payment_fk = "
					+ "b.customer_payment_id and order_id = " + orderId;
			Query query = session.createQuery(queryString);
			return (CustomerPayment) query.list().get(0);
		} catch (NoResultException e) {
			return (CustomerPayment) Collections.emptyList();
		}
	}
	
	public CustomerPayment findPaymentByCustomerId(Short orderId){
		try {
			SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory(); 
			Session session = factory.getCurrentSession();
			session.beginTransaction();
			String queryString = "select b.* from customer a, customer_payment b where b.customer_fk = "
					+ "a.customer_id and customer_id = " + orderId;
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
			String queryString = "select * from Customer_Payment";
			Query query = session.createQuery(queryString);
			List<CustomerPayment> results = query.list();
			return results;
		} catch (NoResultException e) {
			return Collections.emptyList();
		}
	}	
}
