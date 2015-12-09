package edu.luc.webservices.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import edu.luc.webservices.model.CustomerPayment;
import edu.luc.webservices.services.PaymentServiceInterface;
import edu.luc.webservices.services.workflow.PaymentActivity;

@Path("/payments")
public class PaymentResource implements PaymentServiceInterface{
	private PaymentActivity paymentActivity = new PaymentActivity();

	@GET
	@Path("/{id}")
	@Produces(MediaType.TEXT_HTML)
	public String findPaymentByOrderId(@PathParam("id") Short id) {
		CustomerPayment p;
		String ret ="";
		p = paymentActivity.findPaymentByOrderId(id);
		if (p != null){
			ret = p.toString();
		}else{
			ret = "failed to find payment for order with id: " + id;
		}	
		paymentActivity.closeConnection();
		return ret;
	}
	
	@GET
	@Path("/customers/{customerId}")
	@Produces(MediaType.TEXT_HTML)
	public String findPaymentByCustomerId(
			@PathParam("customerId") Short customerId) {
		CustomerPayment p;
		String ret ="";
		p = paymentActivity.findPaymentByCustomerId(customerId);
		if (p != null){
			ret = p.toString();
		}else{
			ret = "failed to find payment for order with id: " + customerId;
		}	
		paymentActivity.closeConnection();
		return ret;
	}
	
	@GET
	@Path("/findAllPayments")
	@Produces(MediaType.TEXT_HTML)
	public String findAllPayments() {
		String paymentString= "";
		List<CustomerPayment> payments = paymentActivity.findAllPayments();
		
		if (payments == null){
			paymentString = "There are no payments registered";
		}else if (payments.size() == 0){
			paymentString = "There are no payments registered";
		}else{
			for (int i = 0; i < payments.size(); i++) {
				paymentString  = paymentString + payments.get(i);
				paymentString += "<br>";
			}
		}
		paymentActivity.closeConnection();
		return paymentString;
	}
	
}
