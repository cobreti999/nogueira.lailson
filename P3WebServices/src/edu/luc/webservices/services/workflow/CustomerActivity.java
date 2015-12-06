package edu.luc.webservices.services.workflow;
import edu.luc.webservices.model.Customer;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import edu.luc.webservices.dao.CustomerDAO;
import edu.luc.webservices.services.exception.InvalidAddressException;

public class CustomerActivity {

	public Customer findCustomerByName(String name) {
		return CustomerDAO.findByName(name);
	}
	
	public List<Customer> findAllCustomers() {
		return CustomerDAO.findAllCustomers();
	}

	public Customer create(Customer customer) throws InvalidAddressException {
		return createOrUpdate(customer);
	}

	public Customer update(Customer customer) throws InvalidAddressException {
		return createOrUpdate(customer);
	}

	public Customer createOrUpdate(Customer customer)
			throws InvalidAddressException {
		validateAddressAndPayment(customer);
		try {
			SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory(); 
			Session session = factory.getCurrentSession();
			session.beginTransaction();
			if (customer.getCustomerId() != null) {
				session.saveOrUpdate(customer);
				session.getTransaction().commit();
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return customer;
	}

	private void validateAddressAndPayment(Customer customer)
			throws InvalidAddressException {
		if (customer == null || customer.getCustomerAddress() == null)
			throw new InvalidAddressException();
	}

	public boolean validateUserAuth(Customer customer) {
		return CustomerDAO.validateUserAuthentication(customer.getCustomerName(),
				customer.getCustomerCredentials().toString());
	}

}
