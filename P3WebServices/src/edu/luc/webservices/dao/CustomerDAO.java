package edu.luc.webservices.dao;

import edu.luc.webservices.helpers.TestHelper;
import edu.luc.webservices.model.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDAO {
	public static Customer findByName(String name) {
		String query = "SELECT * FROM customer where customer_name = '" + name + "' ORDER BY Customer_ID DESC LIMIT 1;";
		try {
			ResultSet rs = TestHelper.makeQuery(query);
			String customerName = "";
			String customerAddress = "";
			int customerZipCode = 0;
			String customerCity = "";
			String customerCountry = "";
			int customerCellPhone = 0;

			while (rs.next()){
				customerName =  rs.getString("Customer_Name");
				customerAddress = rs.getString("Customer_Address");
				customerZipCode = rs.getInt("Customer_Zipcode");
				customerCity = rs.getString("Customer_City");
				customerCountry = rs.getString("Customer_Country");
				customerCellPhone = rs.getInt("Customer_Cellphone");
			}
			Customer customer = new Customer(customerName, customerAddress, customerZipCode, customerCity, customerCountry, customerCellPhone);
			
			rs.close();
			TestHelper.stmt.close();
			TestHelper.conn.close();
			return customer;

		} catch(SQLException se){
			//Handle errors for JDBC
			se.printStackTrace();
		}catch(Exception e){
			//Handle errors for Class.forName
			e.printStackTrace();
		}
		return null;
	}
	

	public static boolean validateUserAuthentication(String name, String password) {
		String query = "SELECT * FROM customer where customer_name = '" + name + "' AND customer_credentials = '" + password + "' ORDER BY Customer_ID DESC LIMIT 1;";
		ResultSet rs = TestHelper.makeQuery(query);
		if (rs == null)
			return false;
		else 
			return true;
	}
}
