package edu.luc.webservices.services.workflow;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import edu.luc.webservices.dao.ProductDAO;
import edu.luc.webservices.model.CustomerPayment;
import edu.luc.webservices.model.Product;

public class ProductActivity {
	ProductDAO productDAO = new ProductDAO();

	public Product searchById(Short id) {
		if (null == id)
			throw new IllegalArgumentException("id must be entered.");
		return productDAO.findById(id);
	}

	public List<Product> findAllProducts() {
		return productDAO.findAllProducts();
	}

}
