/**
 *
 */
package edu.luc.webservices.resources;

import java.net.URI;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import edu.luc.webservices.model.Customer;
import edu.luc.webservices.services.CustomerServiceInterface;
import edu.luc.webservices.services.exception.InvalidAddressException;
import edu.luc.webservices.services.workflow.CustomerActivity;



@Path("/customers")
public class CustomerResource implements CustomerServiceInterface {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CustomerActivity customerActivity = new CustomerActivity();
	
	@GET
	@Path("/{login}")
	@Produces(MediaType.TEXT_HTML)
	public String findByName(@PathParam("login") String name) {
		String ret;
		Customer c = customerActivity.findCustomerByName(name);
		if (c != null){
			ret = c.toString();
		}else{
			ret = "failed to find customer with name: " + name;
		}
		customerActivity.closeConnection();
		return ret;
	}
	
	@GET
	@Path("/findAllCustomers")
	@Produces(MediaType.TEXT_HTML)
	public String findAllCustomers() {
		String customerString= "";
		List<Customer> customers = customerActivity.findAllCustomers();
		
		if (customers == null){
			customerString = "There are no customers registered";
		}else if (customers.size() == 0){
			customerString = "There are no customers registered";
		}else{
			for (int i = 0; i < customers.size(); i++) {
				customerString  = customerString + customers.get(i);
				customerString += "<br>";
			}
		}
		customerActivity.closeConnection();
		return customerString;
	}

	@POST
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	public Response create(Customer customer) {
		Response response = null;
		try {
			customer = customerActivity.create(customer);
			response = Response
					.created(URI.create("/customers/" + customer.getCustomerName()))
					.entity(customer).build();
		} catch (InvalidAddressException e) {
			response = Response.status(Status.BAD_REQUEST).build();
		} catch (Exception e) {
			e.printStackTrace();
			response = Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
		return response;
	}

	@PUT
	@Path("/{customerId}")
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	public Response update(Customer customer) {
		Response response = null;
		try {
			customer = customerActivity.update(customer);
			response = Response.ok().entity(customer).build();
		} catch (InvalidAddressException e) {
			response = Response.status(Status.BAD_REQUEST).build();
		} catch (Exception e) {
			e.printStackTrace();
			response = Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
		return response;
	}
	
}
