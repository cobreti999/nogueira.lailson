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
		CustomerDAO.session = CustomerDAO.factory.getCurrentSession();
		Customer customer = CustomerDAO.findByName(name);
		setLinks(customer, "get");
		return customer;
	}
	
	public List<Customer> findAllCustomers() {
		CustomerDAO.session = CustomerDAO.factory.getCurrentSession();
		List<Customer> customers = CustomerDAO.findAllCustomers();
		for (int i=0; i < customers.size(); i++){
			setLinks(customers.get(i), "get");
		}
		return customers;
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
			session.saveOrUpdate(customer);
			session.getTransaction().commit();
			setLinks(customer, "create");
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
	
	public void closeConnection(){
		CustomerDAO.session.close();
	}
	
	private void setLinks(Customer customer, String action) {
		Link link = new Link(action, 
			"http://localhost:8080/webservices/webapi/customers/" + customer.getCustomerId());	
		customer.setLinks(link);
	}

}
