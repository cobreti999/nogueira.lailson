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
	@Produces({ "application/json" })
	public Response findByName(@PathParam("login") String name) {
		Customer customer = null;
		Response response = null;

		if (null != name) {
			customer = customerActivity.findCustomerByName(name);
			if (null != customer)
				response = Response.ok().entity(customer).build();
		} else {
			response = Response.status(Status.BAD_REQUEST).build();
		}
		return response;
	}
	
	/*@GET
	@Path("/{login}")
	@Produces(MediaType.APPLICATION_ATOM_XML)
	public Customer findByName(@PathParam("login") String name) {
		Customer customer = null;
		Response response = null;

		if (null != name) {
			customer = customerActivity.findCustomerByName(name);
			if (null != customer){
				response = Response.ok().entity(customer).build();
				System.out.println("achei : " + customer.getCustomerName());
			}
		} else {
			response = Response.status(Status.BAD_REQUEST).build();
		}
		return customer;
	}*/
	
	@GET
	@Path("/findAllCustomers")
	@Produces({ "application/json" })
	public Response findAllCustomers() {
		List<Customer> customers = null;
		Response response = null;
		customers = customerActivity.findAllCustomers();
		if (null != customers)
			response = Response.ok().entity(customers).build();
		return response;
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
