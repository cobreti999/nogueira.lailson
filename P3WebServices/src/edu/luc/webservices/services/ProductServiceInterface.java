package edu.luc.webservices.services;
import javax.ws.rs.core.Response;

public interface ProductServiceInterface {
	public Response retrieve(Short id);
	public Response findAllProducts();
}
