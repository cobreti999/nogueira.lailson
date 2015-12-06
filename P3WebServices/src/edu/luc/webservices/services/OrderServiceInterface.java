package edu.luc.webservices.services;

import java.io.Serializable;

import javax.ws.rs.core.Response;
import edu.luc.webservices.model.Order;

public interface OrderServiceInterface extends Serializable{

	public Response createOrder(Order order);

	public Response cancelOrder(Short orderId, String customerName);

	public Response checkOrderStatus(Short orderId, String customerName);

	public Response findOrderByCustomerLogin(String login);
	
	public Response findAllOrders();
}
