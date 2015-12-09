package edu.luc.webservices.dao;

import edu.luc.webservices.helpers.TestHelper;
import edu.luc.webservices.model.Partner;

import java.sql.ResultSet;
import java.util.Collections;
import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class PartnerDAO {
	
	public static SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory(); 
	public static Session session = factory.getCurrentSession();
	
	public static Partner findByName(String name) {
		try {
			session.beginTransaction();
			String queryString = "FROM Partner where partner_name = '" + name + "' ORDER BY Partner_ID" ;
			Query query = session.createQuery(queryString);
			List<Partner> results = query.list();
			if (results.size() > 0){
				Partner c = results.get(0);
				return c;
			}
			else{
				return null;
			}
		} catch (NoResultException e) {
			return (Partner) Collections.emptyList();
		}
	}
	
	public static List<Partner> findAllPartners() {
		try {
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
		String query = "SELECT * FROM partner where partner_name = '" + name + "' AND partner_credentials = '" + password + "' ORDER BY Partner_ID DESC LIMIT 1;";		ResultSet rs = TestHelper.makeQuery(query);
		if (rs == null)
			return false;
		else 
			return true;
	}
}
