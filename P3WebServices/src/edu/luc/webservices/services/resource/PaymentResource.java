package edu.luc.webservices.services.resource;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;


import edu.luc.webservices.model.CustomerPayment;
import edu.luc.webservices.model.Order;
import edu.luc.webservices.services.PaymentServiceInterface;
import edu.luc.webservices.services.workflow.PaymentActivity;


public class PaymentResource implements PaymentServiceInterface{
	private PaymentActivity paymentActivity = new PaymentActivity();

	@GET
	@Path("{id}")
	@Produces({ "application/json" })
	public Response findPaymentByOrderId(@PathParam("id") Short id) {
		Response response = null;
		if (null != id) {
			CustomerPayment payment = paymentActivity.findPaymentByOrderId(id);
			if (null != payment) {
				response = Response.ok(payment).build();
			} else {
				response = Response.status(Status.NOT_FOUND).build();
			}
		} else {
			response = Response.status(Status.BAD_REQUEST).build();
		}
		return response;
	}

	@GET
	@Produces({ "application/json" })
	@Path("/customers/{customerId}")
	public Response findPaymentByCustomerId(
			@PathParam("customerId") Short customerId) {
		Response response = null;
		if (null != customerId) {
			CustomerPayment payment = paymentActivity
					.findPaymentByCustomerId(customerId);
			if (null != payment) {
				response = Response.ok(payment).build();
			} else {
				response = Response.status(Status.NOT_FOUND).build();
			}
		} else {
			response = Response.status(Status.BAD_REQUEST).build();
		}
		return response;
	}
	
	@GET
	@Path("/findAllPayments")
	@Produces({ "application/json" })
	public Response findAllPayments() {
		List<CustomerPayment> payments = null;
		Response response = null;
		payments = paymentActivity.findAllPayments();
		if (null != payments)
			response = Response.ok().entity(payments).build();
		return response;
	}
}
