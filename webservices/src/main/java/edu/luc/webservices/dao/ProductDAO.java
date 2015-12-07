package edu.luc.webservices.dao;

import java.util.Collections;
import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import edu.luc.webservices.model.Customer;
import edu.luc.webservices.model.Product;

public class ProductDAO {
	public List<Product> searchByTitle(String title) {
		try {
			SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory(); 
			Session session = factory.getCurrentSession();
			session.beginTransaction();
			String queryString = "from Product where product_name = " + title;
			Query query = session.createQuery(queryString);
			List<Product> results = query.list();
			return results;
		} catch (NoResultException e) {
			return Collections.emptyList();
		}
	}

	public List<Product> searchByPartnerId(String id) {
		try {
			SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory(); 
			Session session = factory.getCurrentSession();
			session.beginTransaction();
			String queryString = "from Product where Partner_Partner_ID = " + id;
			Query query = session.createQuery(queryString);
			List<Product> results = query.list();
			return results;
		} catch (NoResultException e) {
			return Collections.emptyList();
		}
	}

	public List<Product> searchByPrice(String price) {
		try {
			SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory(); 
			Session session = factory.getCurrentSession();
			session.beginTransaction();
			String queryString = "from Product where Product_Price = " + price;
			Query query = session.createQuery(queryString);
			List<Product> results = query.list();
			return results;
		} catch (NoResultException e) {
			return Collections.emptyList();
		}
	}
	
	public Product findById(Short id) {
		try {
			SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory(); 
			Session session = factory.getCurrentSession();
			session.beginTransaction();
			String queryString = "from Product where Product_ID = " + id;
			Query query = session.createQuery(queryString);
			List products = query.list();
			return (Product) products.get(0);
		} catch (NoResultException e) {
			return (Product) Collections.emptyList();
		}
	}
	
	public List<Product> findAllProducts() {
		try {
			SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory(); 
			Session session = factory.getCurrentSession();
			session.beginTransaction();
			String queryString = "from Product";
			Query query = session.createQuery(queryString);
			List<Product> results = query.list();
			return results;
		} catch (NoResultException e) {
			return Collections.emptyList();
		}
	}	
}
