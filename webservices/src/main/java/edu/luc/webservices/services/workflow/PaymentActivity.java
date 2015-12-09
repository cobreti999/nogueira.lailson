package edu.luc.webservices.services.workflow;

import java.util.List;

import edu.luc.webservices.dao.CustomerDAO;
import edu.luc.webservices.dao.CustomerPaymentDAO;
import edu.luc.webservices.dao.OrderDAO;
import edu.luc.webservices.model.Customer;
import edu.luc.webservices.model.CustomerPayment;
import edu.luc.webservices.model.Order;


public class PaymentActivity {
	private CustomerPaymentDAO paymentDao = new CustomerPaymentDAO();

	public CustomerPayment findPaymentByOrderId(Short orderId) {
		CustomerPaymentDAO.session = CustomerPaymentDAO.factory.getCurrentSession();
		CustomerPayment payment = paymentDao.findPaymentByOrderId(orderId);
		setLinks(payment, "get");
		return payment;
	}
		
	public CustomerPayment findPaymentByCustomerId(Short customerId) {
		CustomerPaymentDAO.session = CustomerPaymentDAO.factory.getCurrentSession();
		CustomerPayment payment = paymentDao.findPaymentByCustomerId(customerId);
		setLinks(payment, "get");
		return payment;
	}
	
	public List<CustomerPayment> findAllPayments() {
		CustomerPaymentDAO.session = CustomerPaymentDAO.factory.getCurrentSession();
		List<CustomerPayment> payments = paymentDao.findAllPayments();
		for (int i=0; i < payments.size(); i++){
			setLinks(payments.get(i), "get");
		}
		return payments;
	}
	
	public void closeConnection(){
		CustomerPaymentDAO.session.close();
	}
	
	private void setLinks(CustomerPayment payment, String action) {
		Link link = new Link(action, 
			"http://localhost:8080/webservices/webapi/orders/" + payment.getCustomerPaymentId());	
		payment.setLinks(link);
	}
}
