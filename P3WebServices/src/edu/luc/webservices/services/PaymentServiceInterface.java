package edu.luc.webservices.services;

import javax.ws.rs.core.Response;

public interface PaymentServiceInterface {

	public Response findPaymentByOrderId(Short orderId);

	public Response findPaymentByCustomerId(Short customerId);
	
	public Response findAllPayments();

}
