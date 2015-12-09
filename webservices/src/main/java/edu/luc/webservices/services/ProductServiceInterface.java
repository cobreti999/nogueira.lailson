package edu.luc.webservices.services;
import javax.ws.rs.core.Response;

public interface ProductServiceInterface {
	public String retrieve(Short id);
	public String findAllProducts();
}
