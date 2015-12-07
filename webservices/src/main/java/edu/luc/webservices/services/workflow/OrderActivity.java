/**
 * 
 */
package edu.luc.webservices.services.workflow;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import edu.luc.webservices.dao.CustomerDAO;
import edu.luc.webservices.dao.OrderDAO;
import edu.luc.webservices.model.Customer;
import edu.luc.webservices.model.Order;
import edu.luc.webservices.services.exception.OrderNotFoundException;

public class OrderActivity {

	public Order createOrder(Order order) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory(); 
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		session.save(order);
		session.getTransaction().commit();
		return order;
	}

	public Boolean cancelOrder(Short orderId, String customerName) throws OrderNotFoundException {
		Order order = OrderDAO.findOrderById(orderId, customerName);
		if (order == null){
			throw new OrderNotFoundException();
		}else{
			order.setOrderStatus("Canceled");
			try{
				SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory(); 
				Session session = factory.getCurrentSession();
				session.beginTransaction();
				session.merge(order);
				session.getTransaction().commit();
				return true;
			}
			catch(Exception e){
				return false;
			}
		}
	}

	public String checkOrderStatus(Short orderId, String customerName)
			throws OrderNotFoundException {
		Order order = OrderDAO.findOrderById(orderId, customerName);
		if (order == null){
			throw new OrderNotFoundException();
		}else{
			return order.getOrderStatus();
		}
	}

	public Order findOrderByCustomerLogin(String customerName)
			throws OrderNotFoundException {
		Order order = OrderDAO.findOrderByCustomerName(customerName);
		if (order == null){
			throw new OrderNotFoundException();
		}else{
			return order;
		}
	}
	
	public List<Order> findAllOrders() {
		return OrderDAO.findAllOrders();
	}

}
