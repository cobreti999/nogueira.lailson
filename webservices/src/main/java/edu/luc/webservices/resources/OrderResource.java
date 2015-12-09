package edu.luc.webservices.resources;

import java.net.URI;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import edu.luc.webservices.dao.CustomerDAO;
import edu.luc.webservices.model.Customer;
import edu.luc.webservices.model.Order;
import edu.luc.webservices.services.OrderServiceInterface;
import edu.luc.webservices.services.exception.OrderNotFoundException;
import edu.luc.webservices.services.workflow.OrderActivity;


@Path("/orders")
public class OrderResource implements OrderServiceInterface {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private OrderActivity orderActivity = new OrderActivity();

	
	/*@POST
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	public Response createOrder(String stringOrder) {
		orderActivity.parseString(stringOrder);
	return null;
		/*Response response = null;
		
		
		//Customer customer = CustomerDAO.findByName("")
		//Order order = new Order(customer, customerPayment, orderDate, orderFinalPrice, orderShippingMethod, orderEstimateDelivery, orderStatus)
		try {
			if (null != order) {
				order = orderActivity.createOrder(order);
				response = Response
						.created(URI.create("/orders/" + order.getOrderId()))
						.entity(order).build();
			} else {
				response = Response.status(Status.BAD_REQUEST).build();
			}
		} catch (Exception e) {
			response = Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
		return response;
	}*/
	
	@POST
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	public Response createOrder(Order order) {
		Response response = null;
		try {
			if (null != order) {
				order = orderActivity.createOrder(order);
				response = Response
						.created(URI.create("/orders/" + order.getOrderId()))
						.entity(order).build();
			} else {
				response = Response.status(Status.BAD_REQUEST).build();
			}
		} catch (Exception e) {
			response = Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
		return response;
	}

	@PUT
	@Path("/{orderId:[0-9]+}/cancel")
	@Produces({ "application/json" })
	public Response cancelOrder(@PathParam("orderId") Short orderId, String customerName) {
		Response response = null;
		try {
			if (null != orderId) {
				System.out.println("ssss");
				boolean orderCancelled = orderActivity.cancelOrder(orderId, customerName);
				if (orderCancelled){
					System.out.println("teste");
					String canc = "OrderCancelled";
					response = Response.status(Status.OK).entity(canc).build();
				}
			} else {
				response = Response.status(Status.BAD_REQUEST).build();
			}
		} catch (OrderNotFoundException e) {
			response = Response.status(Status.NOT_FOUND).build();
		} catch (Exception e) {
			System.out.println("exception");
			response = Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
		return response;
	}

	@GET
	@Path("/{orderId:[0-9]+}/status")
	@Produces({ "application/json" })
	public Response checkOrderStatus(@PathParam("orderId") Short orderId, String customerName) {
		Response response = null;
		try {
			if (null != orderId) {
				String status = orderActivity.checkOrderStatus(orderId, customerName);
				response = Response.status(Status.OK).entity(status).build();
			} else {
				response = Response.status(Status.BAD_REQUEST).build();
			}
		} catch (OrderNotFoundException e) {
			response = Response.status(Status.NOT_FOUND).build();
		}
		orderActivity.closeConnection();
		return response;
	}
	
	@GET
	@Path("/{login}")
	@Produces(MediaType.TEXT_HTML)
	public String findOrderByCustomerLogin(@PathParam("login") String name) {
		Order o;
		String ret = "";
		try {
			o = orderActivity.findOrderByCustomerLogin(name);
			if (o != null){
				ret = o.toString();
			}else{
				ret = "failed to find order for customer with name: " + name;
			}
		} catch (OrderNotFoundException e) {
			e.printStackTrace();
		}
		ret = "failed to find order for customer with name: " + name;
		orderActivity.closeConnection();
		return ret;
	}
	
	@GET
	@Path("/findAllOrders")
	@Produces(MediaType.TEXT_HTML)
	public String findAllOrders() {
		String orderString= "";
		List<Order> orders = orderActivity.findAllOrders();		
		if (orders == null){
			orderString = "There are no orders registered";
		}else if (orders.size() == 0){
			orderString = "There are no orders registered";
		}else{
			for (int i = 0; i < orders.size(); i++) {
				orderString  = orderString + orders.get(i);
				orderString += "<br>";
			}
		}
		orderActivity.closeConnection();
		return orderString;
	}
	

}
