package edu.luc.webservices.services.workflow;

import java.util.List;
import edu.luc.webservices.dao.ProductDAO;
import edu.luc.webservices.model.Product;

public class ProductActivity {
	ProductDAO productDAO = new ProductDAO();

	public Product searchById(Short id) {
		if (null == id)
			throw new IllegalArgumentException("id must be entered.");
		Product product = productDAO.findById(id);
		setLinks(product, "get");
		return product;
	}

	public List<Product> findAllProducts() {
		ProductDAO.session = ProductDAO.factory.getCurrentSession();
		List<Product> products = productDAO.findAllProducts();
		for (int i=0; i < products.size(); i++){
			setLinks(products.get(i), "get");
		}
		return products;
	}
	
	public void closeConnection(){
		ProductDAO.session.close();
	}
	
	private void setLinks(Product product, String action) {
		Link link = new Link(action, 
			"http://localhost:8080/webservices/webapi/products/" + product.getProductId());	
		product.setLinks(link);
	}
}
