package edu.luc.webservices.resources;

import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;


import edu.luc.webservices.model.Product;


import edu.luc.webservices.services.ProductServiceInterface;
import edu.luc.webservices.services.workflow.ProductActivity;

@Path("/products")
public class ProductResource implements ProductServiceInterface{
	
		private static final long serialVersionUID = 1L;
		private ProductActivity productActivity = new ProductActivity();
		
		@GET
		@Path("{id}")
		@Produces(MediaType.TEXT_HTML)
		public String retrieve(@PathParam("id") Short id) {
			if (null == id)
				throw new WebApplicationException(400);

			Product product = productActivity.searchById(id);

			if (null == product)
				throw new WebApplicationException(404);
			productActivity.closeConnection();
			return product.toString();
		}
		
		@GET
		@Path("/findAllProducts")
		@Produces(MediaType.TEXT_HTML)
		public String findAllProducts() {
			String productString= "";
			List<Product> products = productActivity.findAllProducts();
			
			if (products == null){
				productString = "There are no products registered";
			}else if (products.size() == 0){
				productString = "There are no products registered";
			}else{
				for (int i = 0; i < products.size(); i++) {
					productString  = productString + products.get(i);
					productString += "<br>";
				}
			}
			productActivity.closeConnection();
			return productString;
		}
		
}
