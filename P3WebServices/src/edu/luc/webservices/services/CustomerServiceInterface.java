package edu.luc.webservices.services;

import java.io.Serializable;

import javax.ws.rs.core.Response;
import edu.luc.webservices.model.Customer;

public interface CustomerServiceInterface extends Serializable {
	
	public Response findByName(String login);

	public Response create(Customer customer);

	public Response update(Customer customer);
	
	public Response findAllCustomers();

}
