package edu.luc.webservices.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import edu.luc.webservices.helpers.TestHelper;
import edu.luc.webservices.model.Customer;
import edu.luc.webservices.model.CustomerPayment;

public class CustomerPaymentDAO {
	public static CustomerPayment findCustomerPaymentByCustomerName(String customerName){
		Customer customer = CustomerDAO.findByName(customerName);
		String query = "SELECT * FROM customer_payment where customer_fk = '" + customer.getCustomerId() + "' ORDER BY Customer_ID DESC LIMIT 1;";
		try {
			ResultSet rs = TestHelper.makeQuery(query);
			String customerPaymentBilling = "";
			String customerPaymentDescription = "";

			while (rs.next()){
				customerPaymentBilling =  rs.getString("Customer_Payment_Billing");
				customerPaymentDescription = rs.getString("Customer_Payment_Description");
			}
			rs.close();
			TestHelper.stmt.close();
			TestHelper.conn.close();
			CustomerPayment customerPayment = new CustomerPayment(customer, customerPaymentDescription, customerPaymentBilling);
			return customerPayment;

		} catch(SQLException se){
			//Handle errors for JDBC
			se.printStackTrace();
		}catch(Exception e){
			//Handle errors for Class.forName
			e.printStackTrace();
		}
		return null;
	}
}
