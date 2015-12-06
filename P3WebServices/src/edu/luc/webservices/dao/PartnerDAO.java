package edu.luc.webservices.dao;

import edu.luc.webservices.helpers.TestHelper;
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
		String query = "SELECT * FROM partner where partner_name = '" + name + "' ORDER BY Partner_ID DESC LIMIT 1;";
		try {
			ResultSet rs = TestHelper.makeQuery(query);
			String partnerName = "";
			String partnerAddress = "";
			String partnerZipCode = "";
			String partnerCity = "";
			String partnerCountry = "";
			String partnerCellPhone = "";

			while (rs.next()){
				partnerName =  rs.getString("Partner_Name");
				partnerAddress = rs.getString("Partner_Address");
				partnerZipCode = rs.getString("Partner_Zipcode");
				partnerCity = rs.getString("Partner_City");
				partnerCountry = rs.getString("Partner_Country");
				partnerCellPhone = rs.getString("Partner_Cellphone");
			}
			Partner partner = new Partner(partnerName, partnerAddress, partnerZipCode, partnerCity, partnerCountry, partnerCellPhone);
			rs.close();
			TestHelper.stmt.close();
			TestHelper.conn.close();
			return partner;

		} catch(SQLException se){
			//Handle errors for JDBC
			se.printStackTrace();
		}catch(Exception e){
			//Handle errors for Class.forName
			e.printStackTrace();
		}
		return null;
	}
	
	public static List<Partner> findAllPartners() {
		try {
			SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory(); 
			Session session = factory.getCurrentSession();
			session.beginTransaction();
			String queryString = "select * from Partner";
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
