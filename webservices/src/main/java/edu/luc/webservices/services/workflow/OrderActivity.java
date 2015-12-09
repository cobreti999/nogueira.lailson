/**
 * 
 */
package edu.luc.webservices.services.workflow;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import edu.luc.webservices.dao.OrderDAO;
import edu.luc.webservices.model.Order;
import edu.luc.webservices.services.exception.OrderNotFoundException;

public class OrderActivity {
	
	/*public LinkedList<String> parseString(String parseString){
		LinkedList<String> ret = new LinkedList<String>();
		String[] values = parseString.split(",");
		for (int i =0; i < values.length; i++){
			String[] values2 = values[i].split(":");
			String temp = values[1].replace("\"", "");
			ret.add(temp.replace("\\", ""));
		}
		System.out.println(ret);
		return null;
	}*/
	
	public Order createOrder(Order order) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory(); 
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		session.save(order);
		session.getTransaction().commit();
		setLinks(order, "create");
		return order;
	}

	public Boolean cancelOrder(Short orderId, String customerName) throws OrderNotFoundException {
		OrderDAO.session = OrderDAO.factory.getCurrentSession();
		
		Order order = OrderDAO.findOrderById(orderId, customerName);
		if (order == null){
			throw new OrderNotFoundException();
		}else{
			order.setOrderStatus("Canceled");
			try{
				closeConnection();
				OrderDAO.session = OrderDAO.factory.getCurrentSession();
				OrderDAO.session.beginTransaction();
				OrderDAO.session.merge(order);
				OrderDAO.session.getTransaction().commit();
				setLinks(order, "cancel");
				return true;
			}
			catch(Exception e){
				return false;
			}
		}
	}

	public String checkOrderStatus(Short orderId, String customerName)
			throws OrderNotFoundException {
		OrderDAO.session = OrderDAO.factory.getCurrentSession();
		Order order = OrderDAO.findOrderById(orderId, customerName);
		if (order == null){
			throw new OrderNotFoundException();
		}else{
			setLinks(order, "status");
			return order.getOrderStatus();
		}
	}

	public Order findOrderByCustomerLogin(String customerName)
			throws OrderNotFoundException {
		Order order = OrderDAO.findOrderByCustomerName(customerName);
		if (order == null){
			throw new OrderNotFoundException();
		}else{
			setLinks(order, "get");
			return order;
		}
	}
	
	public List<Order> findAllOrders() {
		OrderDAO.session = OrderDAO.factory.getCurrentSession();
		return OrderDAO.findAllOrders();
	}
	
	public void closeConnection(){
		OrderDAO.session.close();
	}

	private void setLinks(Order order, String action) {
		Link link = new Link(action, 
			"http://localhost:8080/webservices/webapi/orders/" + order.getOrderId());	
		order.setLinks(link);
	}
}
