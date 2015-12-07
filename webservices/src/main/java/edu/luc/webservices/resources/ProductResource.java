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
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import edu.luc.webservices.model.CustomerPayment;
import edu.luc.webservices.model.Product;

import edu.luc.webservices.services.ProductServiceInterface;
import edu.luc.webservices.services.workflow.ProductActivity;

@Path("/books")
public class ProductResource implements ProductServiceInterface{
	
		private static final long serialVersionUID = 1L;
		private ProductActivity productActivity = new ProductActivity();

		@GET
		@Path("{id}")
		@Produces({ "application/json" })
		public Response retrieve(@PathParam("id") Short id) {
			if (null == id)
				throw new WebApplicationException(400);

			Product product = productActivity.searchById(id);

			if (null == product)
				throw new WebApplicationException(404);

			return Response.status(Status.OK).entity(product).build();
		}
		
		@GET
		@Path("/findAllProducts")
		@Produces({ "application/json" })
		public Response findAllProducts() {
			List<Product> products = null;
			Response response = null;
			products = productActivity.findAllProducts();
			if (null != products)
				response = Response.ok().entity(products).build();
			return response;
		}
}
