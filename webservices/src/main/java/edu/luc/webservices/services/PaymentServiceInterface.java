package edu.luc.webservices.services;

import javax.ws.rs.core.Response;

public interface PaymentServiceInterface {

	public String findPaymentByOrderId(Short orderId);

	public String findPaymentByCustomerId(Short customerId);
	
	public String findAllPayments();

}
