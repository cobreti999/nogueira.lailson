package edu.luc.webservices.test;

import static org.junit.Assert.assertTrue;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import edu.luc.webservices.helpers.TestHelper;
import edu.luc.webservices.model.Customer;
import edu.luc.webservices.model.CustomerPayment;
import edu.luc.webservices.model.Order;
import edu.luc.webservices.model.Partner;
import edu.luc.webservices.model.Product;
import edu.luc.webservices.model.ProductCategory;

public class TestClass {
	
	@Test
	public void testCustomerCreation() throws SQLException{
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory(); 
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		Customer testCustomer = new Customer("Lailson","6437 North Glenwood Avenue", 60626, "Chicago", "USA", 312478050);
		session.save(testCustomer);
		session.getTransaction().commit();
		
		ResultSet rs = TestHelper.queryDatabase("customer");
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
		
		rs.close();
		TestHelper.stmt.close();
		TestHelper.conn.close();
		//ER1
		String expectedValue1 = "Lailson";
		String actualValue1 = customerName;
		String failureDetails1 = "ER1: The customer name is different than expected. Expected Value: " + expectedValue1 + ". Actual Value: " + actualValue1;
		assertTrue(failureDetails1, actualValue1.equals(expectedValue1));

		//ER2
		String expectedValue2 = "6437 North Glenwood Avenue";
		String actualValue2 = customerAddress;
		String failureDetails2 = "ER2: The customer address is different than expected. Expected Value: " + expectedValue2 + ". Actual Value: " + actualValue2;
		assertTrue(failureDetails2, actualValue2.equals(expectedValue2));

		//ER3
		int expectedValue3 = 60626;
		int actualValue3 = customerZipCode;
		String failureDetails3 = "ER3: The customer zip code is different than expected. Expected Value: " + expectedValue3 + ". Actual Value: " + actualValue3;
		assertTrue(failureDetails3, actualValue3 == expectedValue3);

		//ER4
		String expectedValue4 = "Chicago";
		String actualValue4 = customerCity;
		String failureDetails4 = "ER4: The customer city is different than expected. Expected Value: " + expectedValue4 + ". Actual Value: " + actualValue4;
		assertTrue(failureDetails4, actualValue4.equals(expectedValue4));

		//ER5
		String expectedValue5 = "USA";
		String actualValue5 = customerCountry;
		String failureDetails5 = "ER5: The customer country is different than expected. Expected Value: " + expectedValue5 + ". Actual Value: " + actualValue5;
		assertTrue(failureDetails5, actualValue5.equals(expectedValue5));

		//ER6
		int expectedValue6 = 312478050;
		int actualValue6 = customerCellPhone;
		String failureDetails6 = "ER6: The customer name is different than expected. Expected Value: " + expectedValue6 + ". Actual Value: " + actualValue6;
		assertTrue(failureDetails6, actualValue6 == expectedValue6);		
	} 

	@Test
	public void testCreditCardPayment() throws SQLException{
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory(); 
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		Customer testCustomer = new Customer("Lailson","6437 North Glenwood Avenue", 60626, "Chicago", "USA", 312478050);
		CustomerPayment testPayment = new CustomerPayment(testCustomer, "Debit Card - 1234 5678 1919 2020", "6437 North Glenwood Avenue");
		session.save(testCustomer);
		session.save(testPayment);
		session.getTransaction().commit();
		
		ResultSet rs = TestHelper.queryDatabase("customer_payment");
		String customerPaymentBilling = "";
		String customerPaymentDescription = "";

		while (rs.next()){
			customerPaymentBilling =  rs.getString("Customer_Payment_Billing");
			customerPaymentDescription = rs.getString("Customer_Payment_Description");
		}
		
		rs.close();
		TestHelper.stmt.close();
		TestHelper.conn.close();
		//ER1
		String expectedValue1 = "6437 North Glenwood Avenue";
		String actualValue1 = customerPaymentBilling;
		String failureDetails1 = "ER1: The customer payment billing is different than expected. Expected Value: " + expectedValue1 + ". Actual Value: " + actualValue1;
		assertTrue(failureDetails1, actualValue1.equals(expectedValue1));

		//ER2
		String expectedValue2 = "Debit Card - 1234 5678 1919 2020";
		String actualValue2 = customerPaymentDescription;
		String failureDetails2 = "ER2: The customer payment description is different than expected. Expected Value: " + expectedValue2 + ". Actual Value: " + actualValue2;
		assertTrue(failureDetails2, actualValue2.equals(expectedValue2));
	}
	
	@Test
	public void testPartnerCreation() throws SQLException{
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory(); 
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		Partner partner = new Partner("Amazon", "1234 North Michigan Avenue", "12345", "Seatle", "USA", "3124780500");
		session.save(partner);
		session.getTransaction().commit();
		
		ResultSet rs = TestHelper.queryDatabase("partner");
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
		
		rs.close();
		TestHelper.stmt.close();
		TestHelper.conn.close();
		//ER1
		String expectedValue1 = "Amazon";
		String actualValue1 = partnerName;
		String failureDetails1 = "ER1: The partner name is different than expected. Expected Value: " + expectedValue1 + ". Actual Value: " + actualValue1;
		assertTrue(failureDetails1, actualValue1.equals(expectedValue1));

		//ER2
		String expectedValue2 = "1234 North Michigan Avenue";
		String actualValue2 = partnerAddress;
		String failureDetails2 = "ER2: The partner address is different than expected. Expected Value: " + expectedValue2 + ". Actual Value: " + actualValue2;
		assertTrue(failureDetails2, actualValue2.equals(expectedValue2));

		//ER3
		String expectedValue3 = "12345";
		String actualValue3 = partnerZipCode;
		String failureDetails3 = "ER3: The partner zip code is different than expected. Expected Value: " + expectedValue3 + ". Actual Value: " + actualValue3;
		assertTrue(failureDetails3, actualValue3.equals(expectedValue3));

		//ER4
		String expectedValue4 = "Seatle";
		String actualValue4 = partnerCity;
		String failureDetails4 = "ER4: The partner city is different than expected. Expected Value: " + expectedValue4 + ". Actual Value: " + actualValue4;
		assertTrue(failureDetails4, actualValue4.equals(expectedValue4));

		//ER5
		String expectedValue5 = "USA";
		String actualValue5 = partnerCountry;
		String failureDetails5 = "ER5: The partner country is different than expected. Expected Value: " + expectedValue5 + ". Actual Value: " + actualValue5;
		assertTrue(failureDetails5, actualValue5.equals(expectedValue5));

		//ER6
		String expectedValue6 = "3124780500";
		String actualValue6 = partnerCellPhone;
		String failureDetails6 = "ER6: The partner cellphone is different than expected. Expected Value: " + expectedValue6 + ". Actual Value: " + actualValue6;
		assertTrue(failureDetails6, actualValue6.equals(expectedValue6));
	}
	
	@Test
	public void testProductCreation() throws SQLException{
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory(); 
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		Partner partner = new Partner("Amazon", "1234 North Michigan Avenue", "12345", "Seatle", "USA", "3124780500");
		ProductCategory productCategory = new ProductCategory("TV");
		Product product = new Product(partner, productCategory, "TV Samsung", "2000");	
		session.save(partner);
		session.save(productCategory);
		session.save(product);
		session.getTransaction().commit();
		ResultSet rs = TestHelper.queryDatabase("product");
		String productName = "";
		String productPrice = "";

		while (rs.next()){
			productName =  rs.getString("Product_Name");
			productPrice = rs.getString("Product_Price");
		}
		
		rs.close();
		TestHelper.stmt.close();
		TestHelper.conn.close();
		//ER1
		String expectedValue1 = "TV Samsung";
		String actualValue1 = productName;
		String failureDetails1 = "ER1: The product name is different than expected. Expected Value: " + expectedValue1 + ". Actual Value: " + actualValue1;
		assertTrue(failureDetails1, actualValue1.equals(expectedValue1));

		//ER2
		String expectedValue2 = "2000";
		String actualValue2 = productPrice;
		String failureDetails2 = "ER2: The product price is different than expected. Expected Value: " + expectedValue2 + ". Actual Value: " + actualValue2;
		assertTrue(failureDetails2, actualValue2.equals(expectedValue2));		
	}
	
	@Test
	public void testMakeOrder() throws SQLException, ParseException{
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory(); 
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		Customer customer = new Customer("Lailson","6437 North Glenwood Avenue", 60626, "Chicago", "USA", 312478050);
		CustomerPayment payment = new CustomerPayment(customer, "Debit Card - 1234 5678 1919 2020", "6437 North Glenwood Avenue");
		Partner partner = new Partner("Amazon", "1234 North Michigan Avenue", "12345", "Seatle", "USA", "3124780500");
		ProductCategory productCategory = new ProductCategory("TV");
		Product product = new Product(partner, productCategory, "TV Samsung", "2000");
		Set<Product> products = new HashSet<Product>(0);
		products.add(product);
		
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date myDate = null;
		myDate = formatter.parse("2015-09-27");
		java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());

		int orderFinalPrice = 2000;
		String orderShippingMethod = "Express";
		String orderEstimateDelivery = "3 days";
		String orderStatus = "Shipped";
		
		Order order = new Order(customer, payment, sqlDate, orderFinalPrice, orderShippingMethod, orderEstimateDelivery, orderStatus, products);
		
		session.save(customer);
		session.save(payment);
		session.save(partner);
		session.save(productCategory);
		session.save(product);
		session.save(order);
		session.getTransaction().commit();
		ResultSet rs = TestHelper.queryDatabase("orders");
		Date orderDate = null;

		while (rs.next()){
			orderDate =  rs.getDate("Order_Date");
			orderEstimateDelivery = rs.getString("Order_EstimateDelivery");
			orderFinalPrice = rs.getInt("Order_FinalPrice");
			orderShippingMethod = rs.getString("Order_ShippingMethod");
			orderStatus = rs.getString("Order_Status");
		}		
		
		rs.close();
		TestHelper.stmt.close();
		TestHelper.conn.close();
		//ER1
		String expectedValue1 = "2015-09-27";
		String actualValue1 = orderDate.toString();
		String failureDetails1 = "ER1: The order date is different than expected. Expected Value: " + expectedValue1 + ". Actual Value: " + actualValue1;
		assertTrue(failureDetails1, actualValue1.equals(expectedValue1));

		//ER2
		String expectedValue2 = "3 days";
		String actualValue2 = orderEstimateDelivery;
		String failureDetails2 = "ER2: The order estimate delivery is different than expected. Expected Value: " + expectedValue2 + ". Actual Value: " + actualValue2;
		assertTrue(failureDetails2, actualValue2.equals(expectedValue2));
		
		//ER3
		int expectedValue3 = 2000;
		int actualValue3 = orderFinalPrice;
		String failureDetails3 = "ER3: The order final price is different than expected. Expected Value: " + expectedValue3 + ". Actual Value: " + actualValue3;
		assertTrue(failureDetails3, actualValue3 == expectedValue3);

		//ER4
		String expectedValue4 = "Express";
		String actualValue4 = orderShippingMethod;
		String failureDetails4 = "ER4: The order shipping method is different than expected. Expected Value: " + expectedValue4 + ". Actual Value: " + actualValue4;
		assertTrue(failureDetails4, actualValue4.equals(expectedValue4));

		//ER5
		String expectedValue5 = "Shipped";
		String actualValue5 = orderStatus;
		String failureDetails5 = "ER5: The order status is different than expected. Expected Value: " + expectedValue5 + ". Actual Value: " + actualValue5;
		assertTrue(failureDetails5, actualValue5.equals(expectedValue5));
	}
}
