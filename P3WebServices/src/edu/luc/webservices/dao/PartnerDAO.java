package edu.luc.webservices.dao;

import edu.luc.webservices.helpers.TestHelper;
import edu.luc.webservices.model.Customer;
import edu.luc.webservices.model.Partner;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class PartnerDAO {
	public static Partner findByName(String name) {
		try {
			SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory(); 
			Session session = factory.getCurrentSession();
			session.beginTransaction();
			String queryString = "FROM Partner where partner_name = '" + name + "' ORDER BY Partner_ID" ;
			Query query = session.createQuery(queryString);
			List<Partner> results = query.list();
			return results.get(0);
		} catch (NoResultException e) {
			return (Partner) Collections.emptyList();
		}
	}
	
	public static List<Partner> findAllPartners() {
		try {
			SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory(); 
			Session session = factory.getCurrentSession();
			session.beginTransaction();
			String queryString = "from Partner";
			Query query = session.createQuery(queryString);
			List<Partner> results = query.list();
			return results;
		} catch (NoResultException e) {
			return Collections.emptyList();
		}
	}	

	public static boolean validateUserAuthentication(String name, String password) {
		String query = "SELECT * FROM partner where partner_name = '" + name + "' AND partner_credentials = '" + password + "' ORDER BY Partner_ID DESC LIMIT 1;";
		ResultSet rs = TestHelper.makeQuery(query);
		if (rs == null)
			return false;
		else 
			return true;
	}
}
