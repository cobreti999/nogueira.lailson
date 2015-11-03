package edu.luc.webservices.services.resource;

import java.net.URI;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

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
				boolean orderCancelled = orderActivity.cancelOrder(orderId, customerName);
				response = Response.ok().entity(orderCancelled).build();
			} else {
				response = Response.status(Status.BAD_REQUEST).build();
			}
		} catch (OrderNotFoundException e) {
			response = Response.status(Status.NOT_FOUND).build();
		} catch (Exception e) {
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
		return response;
	}

	@GET
	@Path("/{login}")
	@Produces({ "application/json" })
	public Response findOrderByCustomerLogin(@PathParam("login") String login) {
		Response response = null;
		Order order = null;
		try {
			order = orderActivity.findOrderByCustomerLogin(login);
			response = Response.ok().entity(order).build();
		} catch (OrderNotFoundException e) {
			response = Response.status(Status.NOT_FOUND).build();
		}
		return response;
	}

}
