package edu.luc.webservices.services;

import java.io.Serializable;

import javax.ws.rs.core.Response;
import edu.luc.webservices.model.Customer;

public interface CustomerServiceInterface extends Serializable {
	
	public String findByName(String login);

	public Response create(Customer customer);

	public Response update(Customer customer);
	
	public String findAllCustomers();
}
