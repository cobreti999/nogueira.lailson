package edu.luc.webservices.services.workflow;

import java.util.List;

import edu.luc.webservices.dao.CustomerPaymentDAO;
import edu.luc.webservices.dao.OrderDAO;
import edu.luc.webservices.model.CustomerPayment;
import edu.luc.webservices.model.Order;


public class PaymentActivity {
	private CustomerPaymentDAO paymentDao = new CustomerPaymentDAO();

	public CustomerPayment findPaymentByOrderId(Short orderId) {
		return paymentDao.findPaymentByOrderId(orderId);
	}
		
	public CustomerPayment findPaymentByCustomerId(Short customerId) {
		return paymentDao.findPaymentByCustomerId(customerId);
	}
	
	public List<CustomerPayment> findAllPayments() {
		return paymentDao.findAllPayments();
	}
}
